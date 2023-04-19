package racingcar.dao;

import racingcar.dao.entity.PlayerEntity;

import java.util.List;
import java.util.Optional;

public interface PlayerDao {
    Long save(final String name);

    Optional<PlayerEntity> findByName(final String name);

    Optional<PlayerEntity> findById(final Long id);

    List<PlayerEntity> findAll();
}
