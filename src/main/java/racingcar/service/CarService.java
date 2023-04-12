package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayerDao;
import racingcar.dto.GameInfo;
import racingcar.dto.WinnerCarDto;
import racingcar.exception.DuplicateCarNameException;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.utils.RacingNumberGenerator;
import racingcar.wrapper.Round;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final RacingNumberGenerator generator;
    private final PlayerDao playerDao;
    private final PlayResultDao playResultDao;

    public CarService(final RacingNumberGenerator generator, final PlayerDao playerDao, final PlayResultDao playResultDao) {
        this.generator = generator;
        this.playerDao = playerDao;
        this.playResultDao = playResultDao;
    }

    public WinnerCarDto playGame(GameInfo gameInfo) {
        final Cars cars = setUpGame(gameInfo);
        final WinnerCarDto winner = cars.getWinner();
        save(gameInfo, winner);
        return winner;
    }

    private void save(final GameInfo gameInfo, final WinnerCarDto winner) {
        final long id = insertPlayResult(gameInfo, winner);
        insertPlayers(winner, id);
    }

    private Cars setUpGame(final GameInfo gameInfo) {
        final Cars cars = generateCars(gameInfo.getNames());
        Round round = new Round(gameInfo.getCount());

        race(cars, round);
        return cars;
    }

    private void insertPlayers(final WinnerCarDto winner, final long id) {
        winner.getRacingCars()
                .forEach((carDto -> playerDao.insert(carDto.getName(), carDto.getPosition(), id)));
    }

    private long insertPlayResult(final GameInfo gameInfo, final WinnerCarDto winner) {
        final String winners = String.join(",", winner.getWinners());
        final int trialCount = Integer.parseInt(gameInfo.getCount());
        return playResultDao.insert(winners, trialCount);
    }

    private void race(final Cars cars, final Round round) {
        for (int count = 0; count < round.getRound(); count++) {
            cars.race(generator);
        }
    }

    public Cars generateCars(String inputCarsName) {
        String[] carsName = inputCarsName.split(",");
        checkDuplication(carsName);
        return new Cars(mapToCars(carsName));
    }

    private List<Car> mapToCars(String[] carsName) {
        return Arrays.stream(carsName)
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void checkDuplication(String[] carsName) {
        if (getDistinctCarsCount(carsName) != carsName.length) {
            throw new DuplicateCarNameException();
        }
    }

    private long getDistinctCarsCount(String[] carsName) {
        return Arrays.stream(carsName)
                .distinct()
                .count();
    }
}
