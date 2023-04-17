package racingcar.dao;

import racingcar.entity.GameEntity;

public interface GameDao {
    int saveAndGetId(final GameEntity game);
}
