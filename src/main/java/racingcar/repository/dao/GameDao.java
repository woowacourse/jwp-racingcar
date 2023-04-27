package racingcar.repository.dao;

import racingcar.repository.entity.GameEntity;

public interface GameDao {

    long save(final GameEntity gameEntity);
}
