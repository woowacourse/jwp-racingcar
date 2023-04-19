package racingcar.dao;

import racingcar.dao.entity.PlayerEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PlayerConsoleDao implements  PlayerDao {
    @Override
    public Long save(final String name) {
        return null;
    }

    @Override
    public Optional<PlayerEntity> findByName(final String name) {
        return Optional.empty();
    }

    @Override
    public Optional<PlayerEntity> findById(final Long id) {
        return Optional.empty();
    }

    @Override
    public List<PlayerEntity> findAll() {
        return Collections.emptyList();
    }
}
