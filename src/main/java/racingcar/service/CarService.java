package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayerDao;
import racingcar.dto.GameDto;
import racingcar.dto.WinnerCarDto;
import racingcar.exception.BadRequestException;
import racingcar.exception.ExceptionMessage;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.strategy.RacingNumberGenerator;
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

    public WinnerCarDto playGame(GameDto gameDto) {
        final Cars cars = initGame(gameDto);
        final Car winner = cars.getWinner();
        final WinnerCarDto winnerCarDto = new WinnerCarDto(cars.findWinnerNames(winner), cars.findPlayers());
        save(gameDto, winnerCarDto);
        return winnerCarDto;
    }

    private void save(final GameDto gameDto, final WinnerCarDto winner) {
        final long id = insertPlayResult(gameDto, winner);
        insertPlayers(winner, id);
    }

    private Cars initGame(final GameDto gameDto) {
        final Cars cars = generateCars(gameDto.getNames());
        Round round = new Round(gameDto.getCount());

        race(cars, round);
        return cars;
    }

    private void insertPlayers(final WinnerCarDto winner, final long id) {
        winner.getRacingCars()
                .forEach((carDto -> playerDao.insert(carDto.getName(), carDto.getPosition(), id)));
    }

    private long insertPlayResult(final GameDto gameDto, final WinnerCarDto winner) {
        final String winners = joinWinnerNames(winner);
        final int trialCount = Integer.parseInt(gameDto.getCount());
        return playResultDao.insert(winners, trialCount);
    }

    private String joinWinnerNames(final WinnerCarDto winner) {
        return winner.joinWinnerNames();
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
                .collect(Collectors.toUnmodifiableList());
    }

    private void checkDuplication(String[] carsName) {
        if (getDistinctCarsCount(carsName) != carsName.length) {
            throw new BadRequestException(ExceptionMessage.DUPLICATE_CAR_NAME);
        }
    }

    private long getDistinctCarsCount(String[] carsName) {
        return Arrays.stream(carsName)
                .distinct()
                .count();
    }
}
