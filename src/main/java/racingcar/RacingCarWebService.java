package racingcar;

import java.util.ArrayList;
import java.util.List;
import racingcar.dto.ResultDto;
import racingcar.dto.WinnerDto;
import racingcar.domain.Game;
import racingcar.domain.RandomMoveStrategy;
import racingcar.domain.TryCount;

@org.springframework.stereotype.Service
public class RacingCarWebService {
    private final Game game;
    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public RacingCarWebService(final GameDao gameDao,
                               final PlayerDao playerDao) {
        this.game = new Game();
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    public GameResultDto play(GameDto gameDto) {
        createCars(gameDto);
        TryCount tryCount = new TryCount(gameDto.getTrialCount());
        playGame(tryCount);
        GameResultDto gameResult = save(new TryCount(gameDto.getTrialCount()));
        return gameResult;
    }

    private void createCars(final GameDto gameDto) {
        final List<String> names = gameDto.getNames();
        game.createCars(names);
    }

    private void playGame(TryCount tryCount) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        while (tryCount.isAvailable()) {
            game.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
    }

    private GameResultDto save(TryCount tryCount) {
        WinnerDto winners = findWinners();
        List<ResultDto> racingCars = game.getCarStatuses();
        int gameId = gameDao.insertGame(tryCount).intValue();
        playerDao.insertPlayer(racingCars, winners.getWinners(), gameId);
        return GameResultDto.of(winners, racingCars);
    }

    private WinnerDto findWinners() {
        return game.findWinners();
    }

    public GameResultsDto gameHistory() {
        final List<Integer> gameIds = gameDao.selectGameIds();

        final List<GameResultDto> gameResults = new ArrayList<>();
        for (final Integer gameId : gameIds) {
            final List<String> winners = playerDao.selectWinnerBy(gameId);
            final List<ResultDto> racingCars = playerDao.selectBy(gameId);
            final GameResultDto gameResultDto = new GameResultDto(winners, racingCars);
            gameResults.add(gameResultDto);
        }

        return new GameResultsDto(gameResults);
    }
}
