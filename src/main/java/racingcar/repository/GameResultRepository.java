package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.GameResultDao;
import racingcar.domain.TryCount;
import racingcar.entity.GameResultEntity;

@Repository
public class GameResultRepository {

    private final GameResultDao gameResultDao;

    public GameResultRepository(final GameResultDao gameResultDao) {
        this.gameResultDao = gameResultDao;
    }

    public Long saveGameResult(final TryCount tryCount) {
        final GameResultEntity gameResultEntity = new GameResultEntity(tryCount.getCount());
        return gameResultDao.insert(gameResultEntity);
    }
}
