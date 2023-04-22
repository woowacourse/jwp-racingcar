package racingcar.dao;

import java.util.List;

public interface GamesDao {
    int insert(int trialCount);

    List<Integer> findAllGameIds();
}
