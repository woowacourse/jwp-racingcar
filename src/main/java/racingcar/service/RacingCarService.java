package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.GameRequest;
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

    public int play(final GameRequest gameRequest) {
        List<String> carNames = splitNames(gameRequest);
        RacingGame game = createGame(carNames, gameRequest.getCount());

        race(game, gameRequest.getCount());
        updateWinners(game);

        return game.getId();
    }

    private List<String> splitNames(final GameRequest gameRequest) {
        String names = gameRequest.getNames();

        return List.of(names.split(DELIMITER));
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

    private List<CarDto> convertDto(final List<Car> cars) {
        return cars.stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
    }

    private void updateWinners(final RacingGame racingGame) {
        List<String> winners = racingGame.getWinners();
        winners.forEach(name -> carDao.updateWinner(name, racingGame.getId()));
    }

    public List<CarDto> findWinners(final int gameId) {
        return carDao.findWinners(gameId);
    }

    public List<CarDto> findCars(final int gameId) {
        return carDao.findCars(gameId);
    }
}
