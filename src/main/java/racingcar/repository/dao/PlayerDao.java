package racingcar.repository.dao;

import racingcar.repository.entity.PlayerEntity;

public interface PlayerDao {

    long save(final PlayerEntity playerEntity);

    PlayerEntity findByName(final String name);
}
