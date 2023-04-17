package racingcar.dao.winner;

import java.util.List;

import racingcar.dao.entity.Winner;

public interface WinnerDao {
    void insertWinner(String winners, long gameId);

    List<Winner> findAllWinner();
}
