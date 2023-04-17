package racingcar.dao;

import java.util.List;

public interface GameDao {
    long saveGame(int trialCount);

    List<Long> getGameIds();
}
