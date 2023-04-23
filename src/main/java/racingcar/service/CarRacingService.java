package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.dto.CarInfoDTO;
import racingcar.dao.dto.CarNameDTO;
import racingcar.dao.dto.GameIdDTO;
import racingcar.domain.car.Car;
import racingcar.domain.carfactory.CarFactory;
import racingcar.domain.cars.Cars;
import racingcar.domain.numbergenerator.NumberGenerator;
import racingcar.domain.record.GameRecorder;
import racingcar.domain.result.GameResultOfCar;
import racingcar.domain.system.GameSystem;
import racingcar.service.dto.CarDTO;
import racingcar.service.dto.RacingResultDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarRacingService {
    private static final String DELIMITER = ",";
    private static final int LIMIT = -1;

    private final GameDao gameDao;
    private final CarDao carDao;
    private final NumberGenerator numberGenerator;

    public CarRacingService(final GameDao gameDao, final CarDao carDao, final NumberGenerator numberGenerator) {
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.numberGenerator = numberGenerator;
    }

    @Transactional
    public RacingResultDTO play(final String names, final int count) {
        final GameSystem gameSystem = new GameSystem(count, new GameRecorder(new ArrayList<>()));
        final Long gameId = gameDao.insert(count);

        final Cars cars = makeCars(names);
        gameSystem.executeRace(cars, numberGenerator);
        insertCar(cars, gameId, gameSystem);

        return new RacingResultDTO(
                mapToWinners(carDao.findWinners(gameId)),
                mapToCarDTOs(carDao.findAllCarNamesAndPositions(gameId)));
    }

    private String mapToWinners(final List<CarNameDTO> winners) {
        return winners.stream()
                .map(CarNameDTO::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private List<CarDTO> mapToCarDTOs(final List<CarInfoDTO> carInfoDTOs) {
        return carInfoDTOs.stream()
                .map(carInfoDTO -> new CarDTO(carInfoDTO.getName(), carInfoDTO.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    private Cars makeCars(final String names) {
        List<String> carNames = Arrays.stream(names.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(Collectors.toList());
        CarFactory carFactory = new CarFactory();
        return carFactory.createCars(carNames);
    }

    private void insertCar(final Cars cars, final Long id, final GameSystem gameSystem) {
        for (Car car : cars.getCars()) {
            carDao.insert(car.getName(), car.getPosition(), id, isWin(car, gameSystem));
        }
    }

    private boolean isWin(final Car car, final GameSystem gameSystem) {
        List<GameResultOfCar> winnersGameResult = gameSystem.getWinnersGameResult();
        return winnersGameResult.stream()
                .map(GameResultOfCar::getCarName)
                .anyMatch(winner -> winner.equals(car.getName()));
    }

    @Transactional(readOnly = true)
    public List<RacingResultDTO> showGameResults() {
        final List<GameIdDTO> gameIds = gameDao.findAllGameIds();

        return gameIds.stream()
                .map(gameIdDTO -> new RacingResultDTO(
                        mapToWinners(carDao.findWinners(gameIdDTO.getId())),
                        mapToCarDTOs(carDao.findAllCarNamesAndPositions(gameIdDTO.getId()))))
                .collect(Collectors.toUnmodifiableList());
    }
}
