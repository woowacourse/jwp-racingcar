package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameDto;
import racingcar.dto.GameResponse;
import racingcar.strategy.MovingStrategy;

@Service
public class RacingCarService {

    private static final String DELIMITER = ",";

    private final MovingStrategy movingStrategy;
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingCarService(final MovingStrategy movingStrategy, final GameDao gameDao, final CarDao carDao) {
        this.movingStrategy = movingStrategy;
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public GameResponse play(final GameDto gameDto) {
        RacingGame game = createGame(gameDto.getNames(), gameDto.getCount());
        int gameId = game.getId();

        race(game, gameDto.getCount());
        updateWinners(game);
        String winners = findWinners(gameId);
        List<CarDto> cars = findCars(gameId);

        return GameResponse.of(winners, new ArrayList<>(cars));
    }

    private RacingGame createGame(final List<String> carNames, final int tryTimes) {
        int gameId = gameDao.insertGame(tryTimes);
        RacingGame game = new RacingGame(gameId, CarFactory.buildCars(carNames), movingStrategy);
        List<Car> cars = game.getCars();
        convertDto(cars).forEach(car -> carDao.insertCar(car, gameId));

        return game;
    }

    private void race(final RacingGame racingGame, final int tryTimes) {
        for (int i = 0; i < tryTimes; i++) {
            racingGame.playSingleRound();
        }

        List<Car> cars = racingGame.getCars();
        convertDto(cars).forEach(car -> carDao.updatePosition(car, racingGame.getId()));
    }

    private void updateWinners(final RacingGame racingGame) {
        List<String> winners = racingGame.getWinners();
        winners.forEach(name -> carDao.updateWinner(name, racingGame.getId()));
    }

    private String findWinners(final int gameId) {
        List<CarDto> winners = carDao.findWinners(gameId);
        return winners.stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private List<CarDto> findCars(final int gameId) {
        return carDao.findCars(gameId);
    }

    private List<CarDto> convertDto(final List<Car> cars) {
        return cars.stream()
                .map(car -> CarDto.of(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
