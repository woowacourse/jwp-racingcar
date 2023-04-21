package racingcar.dao;

import racingcar.entity.PlayerEntity;

import java.util.List;

public interface PlayerDao {
    void saveAll(final List<PlayerEntity> players);

    List<PlayerEntity> findAll();
}
