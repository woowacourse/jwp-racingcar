package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Lap;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RandomNumberGenerator;
import racingcar.domain.WinnerMaker;
import racingcar.service.dto.GameRequestDto;
import racingcar.service.dto.GameResponseDto;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;
import racingcar.repository.GameDao;
import racingcar.repository.PlayerResultDao;
import racingcar.util.ListJoiner;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private static final int MINIMUM_RANDOM_NUMBER = 0;
    private static final int MAXIMUM_RANDOM_NUMBER = 9;

    private final GameDao gameDao;
    private final PlayerResultDao playerResultDao;

    @Autowired
    public GameService(final GameDao gameDao, final PlayerResultDao playerResultDao) {
        this.gameDao = gameDao;
        this.playerResultDao = playerResultDao;
    }

    @Transactional
    public GameResponseDto playGame(final GameRequestDto request) {
        final Cars cars = new Cars(request.getNames());
        final Lap lap = new Lap(request.getCount());
        race(cars, lap, new RandomNumberGenerator(MINIMUM_RANDOM_NUMBER, MAXIMUM_RANDOM_NUMBER));
        final List<String> winners = makeWinners(cars);
        final Game game = saveGame(winners, request.getCount());
        final List<PlayerResult> playerResults = savePlayerResults(cars, game);
        return GameResponseDto.of(game, playerResults);
    }

    private void race(final Cars cars, final Lap lap, final NumberGenerator numberGenerator) {
        while (!lap.isFinish()) {
            cars.moveCars(numberGenerator);
            lap.reduce();
        }
    }

    private List<String> makeWinners(final Cars cars) {
        final WinnerMaker winnerMaker = new WinnerMaker();
        return winnerMaker.getWinnerCarsName(cars.getLatestResult());
    }

    private Game saveGame(final List<String> winners, final int trialCount) {
        final Game game = new Game(trialCount, ListJoiner.join(winners));
        return gameDao.save(game);
    }

    private List<PlayerResult> savePlayerResults(final Cars cars, final Game game) {
        final List<PlayerResult> playerResults = new ArrayList<>();
        for (Car car : cars.getLatestResult()) {
            final PlayerResult playerResult = new PlayerResult(
                    car.getCarName().getName(), car.getCurrentPosition().getPosition(), game.getId());
            playerResults.add(playerResultDao.save(playerResult));
        }
        return playerResults;
    }
}
