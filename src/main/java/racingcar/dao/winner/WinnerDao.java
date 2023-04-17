package racingcar.dao.winner;

import java.util.List;

public interface WinnerDao {
    void insertWinner(String winners, long gameId);

    List<String> findWinnersByGameId(Long gameId);
}
