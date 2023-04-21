package racingcar.dao.game;

import java.util.List;

import racingcar.dao.entity.Game;

public interface GameDao {
    long saveGame(Game game);

    List<Long> getGameIds();
}
