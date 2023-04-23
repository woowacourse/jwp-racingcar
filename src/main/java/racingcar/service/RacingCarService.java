package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.database.RacingCarDao;
import racingcar.database.RacingGameDao;
import racingcar.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {
    private static final int START_POSITION = 0;

    private final CarNumberGenerator carNumberGenerator;
    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;

    public RacingCarService() {
        this.carNumberGenerator = new CarRandomNumberGenerator();
        this.racingGameDao = null;
        this.racingCarDao = null;
    }

    @Autowired
    public RacingCarService(final RacingGameDao racingGameDao, final RacingCarDao racingCarDao) {
        this.carNumberGenerator = new CarRandomNumberGenerator();
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
    }

    public RacingCarService(final CarNumberGenerator carNumberGenerator) {
        this.carNumberGenerator = carNumberGenerator;
        this.racingGameDao = null;
        this.racingCarDao = null;
    }

    public RacingCarResult playRacingCar(final List<String> names, final int trialCount) {
        final RacingCars racingCars = generateRacingCars(names);

        race(racingCars, trialCount);
        final RacingCarResult racingCarResult = new RacingCarResult(getWinnerNames(racingCars), racingCars.getCars());

        if (racingGameDao != null && racingCarDao != null) {
            saveResult(trialCount, racingCarResult);
        }
        return racingCarResult;
    }

    private RacingCars generateRacingCars(final List<String> names) {
        final List<Car> cars = names.stream()
                .map(name -> new Car(name, START_POSITION))
                .collect(Collectors.toList());
        return new RacingCars(cars);
    }

    private void race(final RacingCars racingCars, final int trialCount) {
        for (int i = 0; i < trialCount; i++) {
            racingCars.tryOneTime(carNumberGenerator);
        }
    }

    private static List<String> getWinnerNames(final RacingCars racingCars) {
        return racingCars.getWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private void saveResult(final int trialCount, final RacingCarResult racingCarResult) {
        final int gameId = racingGameDao.insert(trialCount);

        racingCarResult.getRacingCars().forEach(car ->
                racingCarDao.insert(car, gameId, racingCarResult.isWinner(car)));
    }

    public List<RacingCarResponse> getRacingCarLog() {
        if (racingGameDao == null || racingCarDao == null) {
            return List.of();
        }

        final List<Integer> gameIds = racingGameDao.selectGameIds();

        return gameIds.stream()
                .map(this::generateLog)
                .collect(Collectors.toUnmodifiableList());
    }

    private RacingCarResponse generateLog(final int gameId) {
        final RacingCarResult result = new RacingCarResult(racingCarDao.selectWinners(gameId), racingCarDao.selectCarsBy(gameId));
        return new RacingCarResponse(result);
    }
}
