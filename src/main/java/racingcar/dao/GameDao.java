package racingcar.dao;

import racingcar.entity.GameEntity;
import racingcar.entity.GameId;

public interface GameDao {
    GameId saveAndGetGameId(final GameEntity game);
}
