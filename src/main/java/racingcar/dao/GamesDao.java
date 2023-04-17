package racingcar.dao;

import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.InsertGameEntity;

@Repository
public class GamesDao {

    private final InsertGameDao insertGameDao;

    public GamesDao(final DataSource dataSource) {
        insertGameDao = new InsertGameDao(dataSource);
    }

    public InsertGameEntity insert(final InsertGameEntity gameEntity) {
        return insertGameDao.insert(gameEntity);
    }
}
