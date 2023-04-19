package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.car.Car;
import racingcar.domain.carfactory.CarFactory;
import racingcar.domain.cars.Cars;
import racingcar.domain.numbergenerator.RandomSingleDigitGenerator;
import racingcar.domain.record.GameRecorder;
import racingcar.domain.result.GameResultOfCar;
import racingcar.domain.system.GameSystem;
import racingcar.dto.CarDTO;
import racingcar.dto.RacingResultDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacingCarService {
    private static final String DELIMITER = ",";

    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingCarService(final GameDao gameDao, final CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public RacingResultDTO play(final String names, final int count) {
        final GameSystem gameSystem = createGameSystem(count);
        final Long gameId = gameDao.insert(count);

        final Cars cars = makeCars(names);
        gameSystem.executeRace(cars, new RandomSingleDigitGenerator());
        insertCar(cars, gameId, gameSystem);

        return createResponseDTO(count, gameSystem);
    }

    private GameSystem createGameSystem(final int gameRound) {
        return new GameSystem(gameRound, new GameRecorder(new ArrayList<>()));
    }

    private Cars makeCars(final String names) {
        List<String> carNames = Arrays.stream(names.split(DELIMITER)).collect(Collectors.toList());
        CarFactory carFactory = new CarFactory();
        return carFactory.createCars(carNames);
    }

    private void insertCar(final Cars cars, final Long id, final GameSystem gameSystem) {
        for (Car car : cars.getCars()) {
            carDao.insert(car.getName(), car.getPosition(), id, isWin(car, gameSystem));
        }
    }

    private boolean isWin(final Car car, final GameSystem gameSystem) {
        List<String> winners = getWinners(gameSystem);
        return winners.contains(car.getName());
    }

    private List<String> getWinners(final GameSystem gameSystem) {
        List<GameResultOfCar> winnersGameResult = gameSystem.getWinnersGameResult();
        return winnersGameResult.stream()
                .map(GameResultOfCar::getCarName)
                .collect(Collectors.toList());
    }

    private RacingResultDTO createResponseDTO(final int count, final GameSystem gameSystem) {
        final String winners = String.join(DELIMITER, getWinners(gameSystem));
        final List<CarDTO> carDTOs = getCarDTOs(count, gameSystem);
        return new RacingResultDTO(winners, carDTOs);
    }

    private List<CarDTO> getCarDTOs(final int count, final GameSystem gameSystem) {
        List<GameResultOfCar> allGameResult = gameSystem.getAllGameResult();
        return allGameResult.stream()
                .filter(gameResultOfCar -> gameResultOfCar.isSameGameRound(count))
                .map(gameResultOfCar -> new CarDTO(gameResultOfCar.getCarName(), gameResultOfCar.getPosition()))
                .collect(Collectors.toList());
    }
}
