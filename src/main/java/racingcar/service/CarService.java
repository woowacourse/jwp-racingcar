package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Round;
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.exception.DuplicateCarNameException;
import racingcar.utils.RacingNumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final RacingNumberGenerator generator;
    private final CarDao carDao;
    private final GameDao gameDao;

    public CarService(final RacingNumberGenerator generator, final CarDao carDao, final GameDao gameDao) {
        this.generator = generator;
        this.carDao = carDao;
        this.gameDao = gameDao;
    }

    public List<GamePlayResponseDto> findGamePlayHistoryAll() {
        return gameDao.findAllWinners().stream()
                .map(gameDto -> new GamePlayResponseDto(gameDto.getWinners(), carDao.findRacingCarByGameId(gameDto.getId())))
                .collect(Collectors.toList());
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

    private long insertPlayResult(final GamePlayRequestDto gamePlayRequestDto, final GamePlayResponseDto winner) {
        final String winners = winner.getWinners();
        final int trialCount = Integer.parseInt(gamePlayRequestDto.getCount());
        return gameDao.save(winners, trialCount);
    }

    private void insertPlayers(final GamePlayResponseDto winner, final long id) {
        winner.getRacingCars()
                .forEach((carDto -> carDao.insert(carDto.getName(), carDto.getPosition(), id)));
    }

    private Cars setUpGame(final GamePlayRequestDto gamePlayRequestDto) {
        final Cars cars = generateCars(gamePlayRequestDto.getNames());
        Round round = new Round(gamePlayRequestDto.getCount());

        race(cars, round);
        return cars;
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
