package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.WinnerEntity;

@Repository
public class WinnerDao {

    private final InsertWinnerDao insertWinnerDao;
    private final SelectWinnerDao selectWinnerDao;

    public WinnerDao(final JdbcTemplate jdbcTemplate) {
        insertWinnerDao = new InsertWinnerDao(jdbcTemplate);
        selectWinnerDao = new SelectWinnerDao(jdbcTemplate);
    }

    public List<WinnerEntity> insertAll(final List<WinnerEntity> winnerEntities) {
        insertWinnerDao.insertAll(winnerEntities);
        return selectWinnerDao.findAllByGameId(winnerEntities.get(0).getGameId());
    }
}
