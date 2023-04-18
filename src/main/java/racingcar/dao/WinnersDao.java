package racingcar.dao;

import java.util.List;

public interface WinnersDao {
    void insert(int gameId, List<Integer> winnerIds);

    List<String> findAllWinnerNameByGameId(int gameId);
}
