package racingcar.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;

@Repository
public class CarDao {

    private final InsertCarDao insertCarDao;
    private final SelectCarDao selectCarDao;

    public CarDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        insertCarDao = new InsertCarDao(dataSource);
        selectCarDao = new SelectCarDao(jdbcTemplate);
    }

    public List<CarEntity> insertAll(final List<CarEntity> carEntities, final int gameId) {
        insertCarDao.saveAll(carEntities, gameId);
        return selectCarDao.findAllByGameId(gameId);
    }
}
