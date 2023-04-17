package racingcar.dao.game;

import java.util.List;

public interface GameDao {
    long saveGame(int trialCount);

    List<Long> getGameIds();
}
