package racingcar.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.InsertGameEntity;
import racingcar.dao.entity.SelectGameEntity;

@Repository
public class GamesDao {

    private final InsertGameDao insertGameDao;
    private final SelectGameDao selectGameDao;

    public GamesDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        insertGameDao = new InsertGameDao(dataSource);
        selectGameDao = new SelectGameDao(jdbcTemplate);
    }

    public InsertGameEntity insert(final InsertGameEntity gameEntity) {
        return insertGameDao.insert(gameEntity);
    }

    public List<SelectGameEntity> findAll() {
        return selectGameDao.findAll();
    }
}
