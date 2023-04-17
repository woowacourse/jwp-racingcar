package racingcar.dao;

import org.springframework.stereotype.Repository;
import racingcar.dto.GameResultResponse;

@Repository
public class RacingGameRepository {

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
}

