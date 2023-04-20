package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.*;
import racingcar.domain.car.Car;
import racingcar.domain.carfactory.CarFactory;
import racingcar.domain.cars.Cars;
import racingcar.domain.numbergenerator.NumberGenerator;
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
    private final NumberGenerator numberGenerator;

    public RacingCarService(final GameDao gameDao, final CarDao carDao, final NumberGenerator numberGenerator) {
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingResultDTO play(final String names, final int count) {
        final GameSystem gameSystem = new GameSystem(count, new GameRecorder(new ArrayList<>()));
        final Long gameId = gameDao.insert(count);

        final Cars cars = makeCars(names);
        gameSystem.executeRace(cars, numberGenerator);
        insertCar(cars, gameId, gameSystem);

        return createResponseDTO(gameSystem);
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

    private RacingResultDTO createResponseDTO(final GameSystem gameSystem) {
        final String winners = String.join(DELIMITER, getWinners(gameSystem));
        final List<CarDTO> carDTOs = getCarDTOs(gameSystem);
        return new RacingResultDTO(winners, carDTOs);
    }

    private List<CarDTO> getCarDTOs(final GameSystem gameSystem) {
        List<GameResultOfCar> finalGameResult = gameSystem.getFinalGameResult();
        return finalGameResult.stream()
                .map(gameResultOfCar -> new CarDTO(gameResultOfCar.getCarName(), gameResultOfCar.getPosition()))
                .collect(Collectors.toList());
    }

    public List<RacingResultDTO> showGameResults() {
        final List<GameIdDTO> gameIds = gameDao.findAllGameIds();
        final List<RacingResultDTO> racingResultDTOs = new ArrayList<>();
        for (GameIdDTO gameId : gameIds) {
            final List<CarNameDTO> winnerNames = carDao.findWinners(gameId.getId());
            final String winners = winnerNames.stream()
                    .map(CarNameDTO::getName)
                    .collect(Collectors.joining(DELIMITER));

            final List<CarNamePositionDTO> carNamesAndPositions = carDao.findAllCarNamesAndPositions(gameId.getId());
            final List<CarDTO> carDTOs = carNamesAndPositions.stream()
                    .map(carNamePositionDTO -> new CarDTO(carNamePositionDTO.getName(), carNamePositionDTO.getPosition()))
                    .collect(Collectors.toList());

            racingResultDTOs.add(new RacingResultDTO(winners, carDTOs));
        }
        return racingResultDTOs;
    }
}
