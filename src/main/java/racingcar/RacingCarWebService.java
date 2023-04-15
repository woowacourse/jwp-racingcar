package racingcar;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dto.RacingCarResultDto;
import racingcar.dto.WinnerDto;
import racingcar.service.RacingCarService;
import racingcar.service.RandomMoveStrategy;
import racingcar.service.TryCount;

@Service
public class RacingCarWebService {
    private final RacingCarService racingCarService;
    private final GameInsertDao gameInsertDao;
    private final PlayerInsertDao playerInsertDao;
    private final GameSelectDao gameSelectDao;
    private final PlayerSelectDao playerSelectDao;

    public RacingCarWebService(final GameInsertDao gameInsertDao,
                               final PlayerInsertDao playerInsertDao,
                               final GameSelectDao gameSelectDao,
                               final PlayerSelectDao playerSelectDao) {
        this.racingCarService = new RacingCarService();
        this.gameInsertDao = gameInsertDao;
        this.playerInsertDao = playerInsertDao;
        this.gameSelectDao = gameSelectDao;
        this.playerSelectDao = playerSelectDao;
    }

    public GameResultDto play(RacingCarGameDto racingCarGameDto) {
        createCars(racingCarGameDto);
        TryCount tryCount = new TryCount(racingCarGameDto.getTrialCount());
        playGame(tryCount);
        GameResultDto gameResult = save(new TryCount(racingCarGameDto.getTrialCount()));
        return gameResult;
    }

    private void createCars(final RacingCarGameDto racingCarGameDto) {
        final List<String> names = racingCarGameDto.getNames();
        racingCarService.createCars(names);
    }

    private void playGame(TryCount tryCount) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        while (tryCount.isAvailable()) {
            racingCarService.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
    }

    private GameResultDto save(TryCount tryCount) {
        WinnerDto winners = findWinners();
        List<RacingCarResultDto> racingCars = racingCarService.getCarStatuses();
        int gameId = gameInsertDao.insertGame(tryCount).intValue();
        playerInsertDao.insertPlayer(racingCars, winners.getWinners(), gameId);
        return GameResultDto.of(winners, racingCars);
    }

    private WinnerDto findWinners() {
        return racingCarService.findWinners();
    }

    public GameResultsDto gameHistory() {
        final List<Integer> gameIds = gameSelectDao.gameIds();

        final List<GameResultDto> gameResults = new ArrayList<>();
        for (final Integer gameId : gameIds) {
            final List<String> winners = playerSelectDao.selectWinnerBy(gameId);
            final List<RacingCarResultDto> racingCars = playerSelectDao.selectBy(gameId);
            final GameResultDto gameResultDto = new GameResultDto(winners, racingCars);
            gameResults.add(gameResultDto);
        }

        return new GameResultsDto(gameResults);
    }
}
