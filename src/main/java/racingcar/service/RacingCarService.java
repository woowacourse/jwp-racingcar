package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
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
import racingcar.dto.ResponseDTO;

@Service
public class RacingCarService {

    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingCarService(final GameDao gameDao, final CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public ResponseDTO play(final List<String> names, final int count) {
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

    private Cars makeCars(final List<String> names) {
        CarFactory carFactory = new CarFactory();
        return carFactory.createCars(names);
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
                .map(gameResultOfCar -> gameResultOfCar.getCarName())
                .collect(Collectors.toList());
    }

    private ResponseDTO createResponseDTO(final int count, final GameSystem gameSystem) {
        final List<String> winners = getWinners(gameSystem);
        final List<CarDTO> carDTOs = getCarDTOs(count, gameSystem);
        return new ResponseDTO(winners, carDTOs);
    }

    private List<CarDTO> getCarDTOs(final int count, final GameSystem gameSystem) {
        List<GameResultOfCar> allGameResult = gameSystem.getAllGameResult();
        return allGameResult.stream()
                .filter(gameResultOfCar -> gameResultOfCar.isSameGameRound(count))
                .map(gameResultOfCar -> new CarDTO(gameResultOfCar.getCarName(), gameResultOfCar.getPosition()))
                .collect(Collectors.toList());
    }
}
