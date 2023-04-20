package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.database.RacingCarDao;
import racingcar.database.RacingGameDao;
import racingcar.model.Car;
import racingcar.model.CarRandomNumberGenerator;
import racingcar.model.RacingCarResult;
import racingcar.model.RacingCars;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {
    private static final int START_POSITION = 0;
    private static final String DELIMITER = ",";

    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;

    @Autowired
    public RacingCarService(final RacingGameDao racingGameDao, final RacingCarDao racingCarDao) {
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
    }

    public RacingCarResult playRacingCar(final List<String> names, final int trialCount) {
        final RacingCars racingCars = generateRacingCars(names);

        race(racingCars, trialCount);

        saveResult(racingCars, trialCount);

        final String joinedWinners = joinWinners(racingCars.getWinners());
        return new RacingCarResult(joinedWinners, racingCars.getCars());
    }

    private RacingCars generateRacingCars(final List<String> names) {
        final List<Car> cars = names.stream()
                .map(name -> new Car(name, START_POSITION))
                .collect(Collectors.toList());
        return new RacingCars(cars);
    }

    private void race(final RacingCars racingCars, final int trialCount) {
        final CarRandomNumberGenerator carRandomNumberGenerator = new CarRandomNumberGenerator();
        for (int i = 0; i < trialCount; i++) {
            racingCars.tryOneTime(carRandomNumberGenerator);
        }
    }

    private void saveResult(final RacingCars racingCars, final int trialCount) {
        final List<Car> winners = racingCars.getWinners();

        final int gameId = racingGameDao.insert(trialCount);
        racingCars.getCars().forEach(car -> racingCarDao.insert(car, gameId, winners.contains(car)));
    }

    private String joinWinners(final List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    public List<RacingCarResult> getRacingCarLog() {
        final List<Integer> gameIds = racingGameDao.selectGameIds();

        return gameIds.stream()
                .map(this::generateLog)
                .collect(Collectors.toList());
    }

    private RacingCarResult generateLog(final int gameId) {
        final String winners = String.join(DELIMITER, racingCarDao.selectWinners(gameId));
        return new RacingCarResult(winners, racingCarDao.selectAllCars(gameId));
    }
}
