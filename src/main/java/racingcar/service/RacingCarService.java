package racingcar.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.JdbcCarDao;
import racingcar.dao.JdbcGameDao;
import racingcar.domain.car.Car;
import racingcar.domain.carfactory.CarFactory;
import racingcar.domain.cars.Cars;
import racingcar.domain.numbergenerator.RandomSingleDigitGenerator;
import racingcar.domain.record.GameRecorder;
import racingcar.domain.result.GameResultOfCar;
import racingcar.domain.system.GameSystem;
import racingcar.dto.CarDTO;
import racingcar.dto.ResponseDTO;

@Service
public class RacingCarService {
    private static final String DELIMITER = ",";

    private final JdbcGameDao gameDao;
    private final JdbcCarDao carDao;

    public RacingCarService(final JdbcGameDao gameDao, final JdbcCarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public ResponseDTO play(final String names, final int count) {
        final List<String> carNames = Arrays.stream(names.split(DELIMITER)).collect(Collectors.toList());

        final Long id = gameDao.insert(count);

        final Cars cars = makeCars(carNames);
        final GameSystem gameSystem = createGameSystem(count);
        gameSystem.executeRace(cars, new RandomSingleDigitGenerator());

        for (Car car : cars.getCars()) {
            carDao.insert(car.getName(), car.getPosition(), id, isWin(car, getWinners(gameSystem)));
        }

        String winners = String.join(DELIMITER, getWinners(gameSystem));
        List<CarDTO> carDTOs = getCarDTOs(count, gameSystem);

        return new ResponseDTO(winners, carDTOs);
    }

    private Cars makeCars(final List<String> carNames) {
        CarFactory carFactory = new CarFactory();
        return carFactory.createCars(carNames);
    }

    private GameSystem createGameSystem(final int gameRound) {
        return new GameSystem(gameRound, new GameRecorder(new ArrayList<>()));
    }

    private List<String> getWinners(final GameSystem gameSystem) {
        List<GameResultOfCar> winnersGameResult = gameSystem.getWinnersGameResult();
        return winnersGameResult.stream()
                .map(gameResultOfCar -> gameResultOfCar.getCarName())
                .collect(Collectors.toList());
    }

    private boolean isWin(final Car car, final List<String> winners) {
        return winners.contains(car.getName());
    }

    private List<CarDTO> getCarDTOs(final int count, final GameSystem gameSystem) {
        List<GameResultOfCar> allGameResult = gameSystem.getAllGameResult();
        return allGameResult.stream()
                .filter(gameResultOfCar -> gameResultOfCar.isSameGameRound(count))
                .map(gameResultOfCar -> new CarDTO(gameResultOfCar.getCarName(), gameResultOfCar.getPosition()))
                .collect(Collectors.toList());
    }
}