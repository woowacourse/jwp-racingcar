package racingcar.dao.winner;

import java.util.List;

import racingcar.dao.entity.Winner;

public interface WinnerDao {
    void insertWinner(List<Winner> winners);

    List<Winner> findAllWinner();
}
