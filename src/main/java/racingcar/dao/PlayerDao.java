package racingcar.dao;

import racingcar.entity.PlayerEntity;

import java.util.List;

interface PlayerDao {

    void save(final long gameId, final List<PlayerEntity> playerEntities);

    List<PlayerEntity> findById(final long gameId);
}
