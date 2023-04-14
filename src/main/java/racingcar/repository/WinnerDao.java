package racingcar.repository;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WinnerDao {

    private final InsertWinnerDao insertWinnerDao;
    private final SelectWinnerDao selectWinnerDao;

    public WinnerDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        insertWinnerDao = new InsertWinnerDao(dataSource);
        selectWinnerDao = new SelectWinnerDao(jdbcTemplate);
    }

    public List<Integer> findByGameId(final int gameId) {
        return selectWinnerDao.findByGameId(gameId);
    }

    public int save(final int gameId, final int winnerCarId) {
        return insertWinnerDao.save(gameId, winnerCarId);
    }
}
