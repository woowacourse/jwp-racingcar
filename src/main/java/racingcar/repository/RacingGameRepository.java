package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dto.GameResultResponse;

import java.util.List;

@Repository
public class RacingGameRepository implements GameRepository {

    private final GameResultDao gameResultDao;
    private final PlayerResultDao playerResultDao;

    public RacingGameRepository(final GameResultDao gameResultDao, final PlayerResultDao playerResultDao) {
        this.gameResultDao = gameResultDao;
        this.playerResultDao = playerResultDao;
    }

    public void saveGameRecord(final GameResultResponse gameResultResponse, final int trialCount) {
        Number gameResultKey = gameResultDao.save(gameResultResponse.getWinners(), trialCount);
        playerResultDao.save(gameResultResponse.getRacingCars(), gameResultKey);
    }

    public List<GameResultResponse> makeGameRecords() {
        GameRecordJoiner gameRecordJoiner = new GameRecordJoiner();
        return gameRecordJoiner.join(gameResultDao.selectAll(), playerResultDao.selectAll());
    }
}

