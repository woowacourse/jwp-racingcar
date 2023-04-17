package racingcar.dao;

import java.util.List;

public interface WinnerDao {
    void insertWinner(String winners, long gameId);

    List<String> findWinnersByGameId(Long gameId);
}
