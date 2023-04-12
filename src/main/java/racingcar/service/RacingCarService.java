package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.strategy.MovingStrategy;

@Service
public class RacingCarService {

    private final MovingStrategy movingStrategy;
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingCarService(final MovingStrategy movingStrategy, final GameDao gameDao, final CarDao carDao) {
        this.movingStrategy = movingStrategy;
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public RacingGame createGame(final List<String> carNames, final int tryTimes) {
        int gameId = gameDao.insertGame(tryTimes);
        RacingGame game = new RacingGame(gameId, CarFactory.buildCars(carNames), movingStrategy);
        List<Car> cars = game.getCars();
        cars.forEach(car -> carDao.insertCar(car, gameId));

        return game;
    }

    public void race(final RacingGame racingGame, final int tryTimes) {
        for (int i = 0; i < tryTimes; i++) {
            racingGame.playSingleRound();
        }

        List<Car> cars = racingGame.getCars();
        cars.forEach(car -> carDao.updatePosition(car, racingGame.getId()));
    }

    public List<String> findWinners(final RacingGame racingGame) {
        return racingGame.getWinners();
    }

    public void updateWinners(final RacingGame racingGame) {
        List<String> winners = racingGame.getWinners();
        winners.forEach(name -> carDao.updateWinner(name, racingGame.getId()));
    }

    public List<Car> findCars(final RacingGame racingGame) {
        return racingGame.getCars();
    }
}
