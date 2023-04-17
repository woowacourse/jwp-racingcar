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
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameInfoDto;
import racingcar.dto.ResultDto;
import racingcar.domain.car.Cars;
import racingcar.domain.manager.CarMoveManager;
import racingcar.util.ValueEditor;

@Service
public class GameService {
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
        RacingGame racingGame = RacingGame.initialize(new Cars(new ArrayList<>()));
        racingGame.createCars(gameInfoDto.getNames());
        racingGame.moveCars(carMoveManager, gameInfoDto.getCount());
        ResultDto resultDto = new ResultDto(racingGame.getWinner(), racingGame.getCarMoveResults());
        saveResult(gameInfoDto.getCount(), resultDto);
        return resultDto;
    }

    private void saveResult(String countInput, ResultDto resultDto) {
        int moveCount = Integer.parseInt(countInput);
        long gameId = gameDao.saveGame(new Game(moveCount));
        saveCars(gameId, resultDto.getRacingCars());
        saveWinners(gameId, resultDto.getWinners());
    }

    private void saveCars(long gameId, List<CarDto> carDtos) {
        List<Car> cars = carDtos.stream()
                .map(carDto -> new Car(gameId, carDto.getName(), carDto.getPosition()))
                .collect(Collectors.toUnmodifiableList());
        carDao.insertCar(cars);
    }

    private void saveWinners(long gameId, String winnerNames) {
        List<Winner> winners = Arrays.stream(winnerNames.split(", "))
                .map(winner -> new Winner(gameId, winner))
                .collect(Collectors.toUnmodifiableList());
        winnerDao.insertWinner(winners);
    }

    public List<ResultDto> getAllResults() {
        List<Long> gameIds = gameDao.getGameIds();
        Map<Long, List<Car>> carsGroupByGameId = getCarsGroupByGameId();
        Map<Long, List<Winner>> winnersGroupByGameId = getWinnersGroupByGameId();
        return getResultDtos(gameIds, carsGroupByGameId, winnersGroupByGameId);
    }

    private Map<Long, List<Car>> getCarsGroupByGameId() {
        List<Car> allCars = carDao.findAllCars();
        return allCars.stream()
                .collect(Collectors.groupingBy(Car::getGameId));
    }

    private Map<Long, List<Winner>> getWinnersGroupByGameId() {
        List<Winner> allWinner = winnerDao.findAllWinner();
        return allWinner.stream()
                .collect(Collectors.groupingBy(Winner::getGameId));
    }

    private List<ResultDto> getResultDtos(
            List<Long> gameIds,
            Map<Long, List<Car>> carsGroupByGameId,
            Map<Long, List<Winner>> winnersGroupByGameId
    ) {
        List<ResultDto> resultDtos = new ArrayList<>();
        for (Long gameId : gameIds) {
            List<CarDto> carDtos = getCarDtos(carsGroupByGameId, gameId);
            String winnersName = getWinnersName(winnersGroupByGameId, gameId);
            resultDtos.add(new ResultDto(winnersName, carDtos));
        }
        return resultDtos;
    }

    private List<CarDto> getCarDtos(Map<Long, List<Car>> carsGroupByGameId, Long gameId) {
        List<Car> cars = carsGroupByGameId.get(gameId);
        return cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private String getWinnersName(Map<Long, List<Winner>> winnersGroupByGameId, Long gameId) {
        List<Winner> winners = winnersGroupByGameId.get(gameId);
        return ValueEditor.joinWithComma(
                winners.stream().map(Winner::getWinner).collect(Collectors.toList()));
    }
}
