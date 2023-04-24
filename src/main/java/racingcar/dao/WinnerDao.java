package racingcar.dao;

import java.util.List;

public interface WinnerDao {

    void insert(List<String> winners, long gameId);

    List<String> selectByGameId(long gameId);
}
