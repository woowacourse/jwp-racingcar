package racingcar.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.WinnerEntity;

@Repository
public class WinnerDao {

    private final InsertWinnerDao insertWinnerDao;
    private final SelectWinnerDao selectWinnerDao;

    public WinnerDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        insertWinnerDao = new InsertWinnerDao(dataSource);
        selectWinnerDao = new SelectWinnerDao(jdbcTemplate);
    }

    public List<WinnerEntity> saveAll(final List<CarEntity> winners, final int gameId) {
        insertWinnerDao.insertAll(winners, gameId);
        return findAllByGameId(gameId);
    }

    public List<WinnerEntity> findAllByGameId(final int gameId) {
        return selectWinnerDao.findAllByGameId(gameId);
    }
}
