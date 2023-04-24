package racingcar.repository.dao;

import racingcar.repository.entity.PlayerEntity;

public interface PlayerDao {

    long save(final PlayerEntity playerEntity);

    boolean existsByName(final String name);

    PlayerEntity findByName(final String name);
}
