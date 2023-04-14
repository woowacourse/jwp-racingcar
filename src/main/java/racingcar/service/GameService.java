package racingcar.service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.response.PlayResponse;
import racingcar.dto.VehicleDto;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TrialCount;
import racingcar.domain.Vehicle;
import racingcar.repository.GameDao;
import racingcar.util.CarNameValidator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private static final String DELIMITER = ",";

    private final CarNameValidator carNameValidator;
    private final RecordService recordService;
    private final GameDao gameDao;

    @Autowired
    public GameService(final CarNameValidator carNameValidator, final RecordService recordService, final GameDao gameDao) {
        this.carNameValidator = carNameValidator;
        this.recordService = recordService;
        this.gameDao = gameDao;
    }

    @Transactional
    public PlayResponse playRacing(final String names, final int count) {
        Cars cars = createCars(names);
        TrialCount trialCount = new TrialCount(count);

        long gameId = saveGame(trialCount);

        play(trialCount, cars);
        recordService.saveResults(gameId, cars);

        return new PlayResponse(findWinnerNames(cars), toVehicleDto(cars));
    }

    private Cars createCars(final String names) {
        List<String> carNames = splitNames(names);

        carNameValidator.validate(carNames);

        return new Cars(carNames.stream()
                .map(Car::new)
                .collect(toUnmodifiableList()));
    }

    private List<String> splitNames(final String names) {
        return Arrays.stream(names.split(DELIMITER, -1))
                .map(String::trim)
                .collect(toList());
    }

    private long saveGame(final TrialCount trialCount) {
        return gameDao.insert(trialCount.getTrialCount());
    }

    private void play(final TrialCount trialCount, final Cars cars) {
        for (int i = 0; i < trialCount.getTrialCount(); i++) {
            cars.moveAll();
        }
    }

    private List<String> findWinnerNames(final Cars cars) {
        return cars.getWinner().stream()
                .map(Vehicle::getName)
                .collect(Collectors.toList());
    }

    private List<VehicleDto> toVehicleDto(final Cars cars) {
        return cars.getCars()
                .stream()
                .map(car -> new VehicleDto(car.getName(), car.getDistance()))
                .collect(Collectors.toList());
    }
}
