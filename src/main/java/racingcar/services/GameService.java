package racingcar.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.dao.car.CarDao;
import racingcar.dao.entity.Car;
import racingcar.dao.entity.Game;
import racingcar.dao.entity.Winner;
import racingcar.dao.game.GameDao;
import racingcar.dao.winner.WinnerDao;
import racingcar.dto.CarDto;
import racingcar.dto.GameInfoDto;
import racingcar.dto.ResultDto;
import racingcar.model.car.Cars;
import racingcar.model.manager.CarMoveManager;
import racingcar.util.ValueEditor;

@Service
public class GameService {
    public static final String MOVE_COUNT_EXCEPTION_MESSAGE = "1회 이상 이동해야 합니다.";
    public static final int MIN_MOVE_COUNT = 1;
    private final GameDao gameDao;
    private final CarDao carDao;
    private final WinnerDao winnerDao;
    private final CarMoveManager carMoveManager;

    public GameService(GameDao gameDao, CarDao carDao, WinnerDao winnerDao, CarMoveManager carMoveManager) {
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.winnerDao = winnerDao;
        this.carMoveManager = carMoveManager;
    }

    public ResultDto play(GameInfoDto gameInfoDto) {
        Cars cars = initialize();
        createCars(cars, gameInfoDto.getNames());
        moveCars(cars, gameInfoDto.getCount());
        ResultDto resultDto = new ResultDto(getWinner(cars), getResult(cars));
        saveResult(gameInfoDto.getCount(), resultDto);
        return resultDto;
    }

    Cars initialize() {
        return new Cars(new ArrayList<>());
    }

    void createCars(Cars cars, String inputs) {
        List<String> names = ValueEditor.splitByComma(inputs);
        cars.createCars(names);
    }

    void moveCars(Cars cars, String countInput) {
        int count = ValueEditor.parseStringToInt(countInput);
        validateCount(count);
        for (int i = 0; i < count; i++) {
            cars.moveAllCarsOnce(carMoveManager);
        }
    }

    private void validateCount(int count) {
        if (count < MIN_MOVE_COUNT) {
            throw new IllegalArgumentException(MOVE_COUNT_EXCEPTION_MESSAGE);
        }
    }

    void saveResult(String countInput, ResultDto resultDto) {
        int moveCount = Integer.parseInt(countInput);
        long gameId = gameDao.saveGame(new Game(moveCount));
        List<Car> cars = resultDto.getRacingCars()
                .stream()
                .map(carDto -> new Car(gameId, carDto.getName(), carDto.getPosition()))
                .collect(Collectors.toUnmodifiableList());
        carDao.insertCar(cars);
        String winnerNames = resultDto.getWinners();
        List<Winner> winners = Arrays.stream(winnerNames.split(", "))
                .map(winner -> new Winner(gameId, winner))
                .collect(Collectors.toUnmodifiableList());
        winnerDao.insertWinner(winners);
    }

    List<CarDto> getResult(Cars cars) {
        return cars.getCurrentResult()
                .stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    String getWinner(Cars cars) {
        return ValueEditor.joinWithComma(cars.getWinners());
    }

    public List<ResultDto> getAllResults() {
        List<ResultDto> resultDtos = new ArrayList<>();
        List<Long> gameIds = gameDao.getGameIds();
        List<Car> allCars = carDao.findAllCars();
        List<Winner> allWinner = winnerDao.findAllWinner();
        Map<Long, List<Car>> carsGroupByGameId = allCars.stream()
                .collect(Collectors.groupingBy(Car::getGameId));
        Map<Long, List<Winner>> winnersGroupByGameId = allWinner.stream()
                .collect(Collectors.groupingBy(Winner::getGameId));
        for (Long gameId : gameIds) {
            List<Winner> winners = winnersGroupByGameId.get(gameId);
            String winnersName = ValueEditor.joinWithComma(winners.stream().map(Winner::getWinner).collect(Collectors.toList()));
            List<Car> cars = carsGroupByGameId.get(gameId);
            List<CarDto> carDtos = cars.stream()
                    .map(car -> new CarDto(car.getName(), car.getPosition()))
                    .collect(Collectors.toList());
            resultDtos.add(new ResultDto(winnersName, carDtos));
        }
        return resultDtos;
    }
}
