package racingcar.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameResultResponse;
import racingcar.persistence.dao.GameResultDao;
import racingcar.persistence.dao.PlayerResultDao;

import java.util.List;

@Repository
public class RacingGameRepository implements GameRepository {

    private final GameResultDao gameResultDao;
    private final PlayerResultDao playerResultDao;

    @Autowired
    public RacingGameRepository(final GameResultDao gameResultDao, final PlayerResultDao playerResultDao) {
        this.gameResultDao = gameResultDao;
        this.playerResultDao = playerResultDao;
    }

    public void saveGameRecord(final GameResultResponse gameResultResponse, final int trialCount) {
        Long gameResultKey = gameResultDao.save(gameResultResponse.getWinners(), trialCount);
        playerResultDao.saveAll(gameResultResponse.getRacingCars(), gameResultKey);
    }

    public List<GameResultResponse> makeGameRecords() {
        GameRecordJoiner gameRecordJoiner = new GameRecordJoiner();
        return gameRecordJoiner.join(gameResultDao.selectAll(), playerResultDao.selectAll());
    }
}

