package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayerDao;
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.exception.DuplicateCarNameException;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.Round;
import racingcar.utils.RacingNumberGenerator;

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

    public GamePlayResponseDto playGame(GamePlayRequestDto gamePlayRequestDto) {
        final Cars cars = setUpGame(gamePlayRequestDto);
        final GamePlayResponseDto winner = cars.getWinner();
        save(gamePlayRequestDto, winner);
        return winner;
    }

    private void save(final GamePlayRequestDto gamePlayRequestDto, final GamePlayResponseDto winner) {
        final long id = insertPlayResult(gamePlayRequestDto, winner);
        insertPlayers(winner, id);
    }

    private Cars setUpGame(final GamePlayRequestDto gamePlayRequestDto) {
        final Cars cars = generateCars(gamePlayRequestDto.getNames());
        Round round = new Round(gamePlayRequestDto.getCount());

        race(cars, round);
        return cars;
    }

    private void insertPlayers(final GamePlayResponseDto winner, final long id) {
        winner.getRacingCars()
                .forEach((carDto -> playerDao.insert(carDto.getName(), carDto.getPosition(), id)));
    }

    private long insertPlayResult(final GamePlayRequestDto gamePlayRequestDto, final GamePlayResponseDto winner) {
        final String winners = String.join(",", winner.getWinners());
        final int trialCount = Integer.parseInt(gamePlayRequestDto.getCount());
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
