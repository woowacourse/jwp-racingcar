package racingcar.repository;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

@Repository
public class CarDao {

    private final InsertCarDao insertCarDao;
    private final SelectCarDao selectCarDao;

    public CarDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        insertCarDao = new InsertCarDao(dataSource);
        selectCarDao = new SelectCarDao(jdbcTemplate);
    }

    public CarEntity save(final Car car, final int gameId) {
        return insertCarDao.save(car, gameId);
    }

    public List<CarEntity> findByGameId(final int gameId) {
        return selectCarDao.findByGameId(gameId);
    }

    public CarEntity findById(final int carId) {
        return selectCarDao.findById(carId);
    }
}
