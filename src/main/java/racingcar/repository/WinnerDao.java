package racingcar.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class WinnerDao {

    private final InsertWinnerDao insertWinnerDao;
    private final SelectWinnerDao selectWinnerDao;

    public WinnerDao(final InsertWinnerDao insertWinnerDao, final SelectWinnerDao selectWinnerDao) {
        this.insertWinnerDao = insertWinnerDao;
        this.selectWinnerDao = selectWinnerDao;
    }

    public List<Integer> findByGameId(final int gameId) {
        return selectWinnerDao.findByGameId(gameId);
    }

    public int save(final int gameId, final int winnerCarId) {
        return insertWinnerDao.save(gameId, winnerCarId);
    }
}
