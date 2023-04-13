package racingcar.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.WinnerDao;
import racingcar.dto.CarDto;
import racingcar.dto.GameInfoDto;
import racingcar.dto.ResultDto;
import racingcar.model.car.Cars;
import racingcar.model.manager.CarMoveManager;
import racingcar.util.MoveCountValidator;

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
        List<String> names = Arrays.asList(inputs.split(","));
            cars.createCars(names);
    }

    void moveCars(Cars cars, String countInput) {
        MoveCountValidator.validate(countInput);
        int count = Integer.parseInt(countInput);
        for (int i = 0; i < count; i++) {
            cars.moveAllCarsOnce(carMoveManager);
        }
    }

    void saveResult(String countInput, ResultDto resultDto) {
        int moveCount = Integer.parseInt(countInput);
        long gameId = gameDao.saveGame(moveCount);
        carDao.insertCar(resultDto.getRacingCars(), gameId);
        winnerDao.insertWinner(resultDto.getWinners(), gameId);
    }

    List<CarDto> getResult(Cars cars) {
        return cars.getCurrentResult()
                .stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    String getWinner(Cars cars) {
        return String.join(", ", cars.getWinners());
    }
}
