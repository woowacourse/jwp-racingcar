package racingcar.dao;

import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.GameEntity;

@Repository
public class GamesDao {

    private final InsertGameDao insertGameDao;

    public GamesDao(final DataSource dataSource) {
        insertGameDao = new InsertGameDao(dataSource);
    }

    public GameEntity save(final GameEntity gameEntity) {
        return insertGameDao.save(gameEntity);
    }
}
