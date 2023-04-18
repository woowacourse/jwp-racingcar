package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;

@Repository
public class CarDao {

    private final InsertCarDao insertCarDao;
    private final SelectCarDao selectCarDao;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        insertCarDao = new InsertCarDao(jdbcTemplate);
        selectCarDao = new SelectCarDao(jdbcTemplate);
    }

    public List<CarEntity> insertAll(final List<CarEntity> carEntities) {
        final Integer gameId = carEntities.get(0).getGameId();
        insertCarDao.insertAll(carEntities);
        return selectCarDao.findAllByGameId(gameId);
    }

    public List<CarEntity> findAll() {
        return selectCarDao.findAll();
    }
}
