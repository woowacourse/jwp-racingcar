package racingcar.dao;

import java.util.List;

public interface GameDao {

    int insertGame(final int tryTimes);

    List<Integer> findAllGamesId();
}
