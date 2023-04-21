package racingcar.dao;

import racingcar.entity.Game;
import racingcar.entity.GameId;

public interface GameDao {
    GameId saveAndGetGameId(final Game game);
}
