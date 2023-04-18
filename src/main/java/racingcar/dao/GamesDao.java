package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.GameEntity;

@Repository
public class GamesDao {

    private final InsertGameDao insertGameDao;

    public GamesDao(final JdbcTemplate jdbcTemplate) {
        insertGameDao = new InsertGameDao(jdbcTemplate);
    }

    public GameEntity save(final GameEntity gameEntity) {
        return insertGameDao.save(gameEntity);
    }
}
