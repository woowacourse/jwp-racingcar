package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.Game;
import racingcar.domain.RandomMoveStrategy;
import racingcar.domain.TryCount;
import racingcar.dto.GameDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.GameResultsDto;
import racingcar.dto.ResultDto;
import racingcar.dto.WinnerDto;

@Service
public final class GameService {
    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public GameService(final GameDao gameDao,
                       final PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    public GameResultDto play(final GameDto gameDto) {
        final Game game = new Game();
        createCars(game, gameDto);
        TryCount tryCount = new TryCount(gameDto.getTrialCount());
        playGame(game, tryCount);
        return save(game, new TryCount(gameDto.getTrialCount()));
    }

    private void createCars(final Game game, final GameDto gameDto) {
        final List<String> names = gameDto.getNames();
        game.createCars(names);
    }

    private void playGame(final Game game, TryCount tryCount) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        while (tryCount.isAvailable()) {
            game.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
    }

    private GameResultDto save(final Game game, TryCount tryCount) {
        WinnerDto winners = findWinners(game);
        List<ResultDto> racingCars = game.getCarStatuses();
        int gameId = gameDao.insertGame(tryCount).intValue();
        playerDao.insertPlayer(racingCars, winners.getWinners(), gameId);
        return GameResultDto.of(winners, racingCars);
    }

    private WinnerDto findWinners(final Game game) {
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
