package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.GameResultDao;
import racingcar.entity.GameResultEntity;

@Repository
public class GameResultRepository {

    private final GameResultDao gameResultDao;

    public GameResultRepository(final GameResultDao gameResultDao) {
        this.gameResultDao = gameResultDao;
    }

    public Long save(final GameResultEntity gameResultEntity) {
        return gameResultDao.insert(gameResultEntity);
    }
}
