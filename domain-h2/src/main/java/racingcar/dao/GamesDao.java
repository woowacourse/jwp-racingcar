package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.GameEntity;

@Repository
public class GamesDao {

    private final InsertGameDao insertGameDao;
    private final SelectGameDao selectGameDao;

    public GamesDao(final JdbcTemplate jdbcTemplate) {
        insertGameDao = new InsertGameDao(jdbcTemplate);
        selectGameDao = new SelectGameDao(jdbcTemplate);
    }

    public GameEntity insert(final GameEntity gameEntity) {
        return insertGameDao.insert(gameEntity);
    }

    public List<GameEntity> findAll() {
        return selectGameDao.findAll();
    }
}
