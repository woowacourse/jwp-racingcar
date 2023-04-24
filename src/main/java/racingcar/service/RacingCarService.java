package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.domain.CarFactory;
import racingcar.domain.RacingGame;
import racingcar.domain.Result;
import racingcar.dto.ResultDto;
import racingcar.strategy.RandomMovingStrategy;

@Transactional
@Service
public class RacingCarService {

    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingCarService(final GameDao gameDao, final CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public ResultDto play(final List<String> carNames, final int trialCount) {
        final RacingGame game = createGame(carNames, trialCount);
        race(game, trialCount);
        updateWinners(game);

        return ResultDto.from(new Result(game.winners(), game.cars()));
    }

    private RacingGame createGame(final List<String> carNames, final int tryTimes) {
        int gameId = gameDao.insertGame(tryTimes);
        RacingGame game = new RacingGame(gameId, CarFactory.buildCars(carNames), new RandomMovingStrategy());
        List<Car> cars = game.cars();
        cars.forEach(car -> carDao.insertCar(car, gameId));

        return game;
    }

    private void race(final RacingGame racingGame, final int tryTimes) {
        racingGame.race(tryTimes);

        List<Car> cars = racingGame.cars();
        cars.forEach(car -> carDao.updatePosition(car, racingGame.id()));
    }

    private void updateWinners(final RacingGame racingGame) {
        List<Car> winners = racingGame.winners();
        winners.forEach(car -> carDao.updateWinner(car.name(), racingGame.id()));
    }

    public List<ResultDto> findAllResults() {
        List<ResultDto> results = new ArrayList<>();
        for (int gameId : gameDao.findAllGamesId()) {
            results.add(ResultDto.from(new Result(findWinners(gameId), findCars(gameId))));
        }
        return results;
    }

    private List<Car> findWinners(final int gameId) {
        return carDao.findWinners(gameId);
    }

    private List<Car> findCars(final int gameId) {
        return carDao.findCars(gameId);
    }
}
