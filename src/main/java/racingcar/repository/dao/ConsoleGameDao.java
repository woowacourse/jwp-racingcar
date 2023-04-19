package racingcar.repository.dao;

import java.util.Collections;
import java.util.List;

import racingcar.repository.entity.GameEntity;

public class ConsoleGameDao implements GameDao {

    @Override
    public long save(final GameEntity gameEntity) {
        return 0;
    }

    @Override
    public List<GameEntity> findAll() {
        return Collections.emptyList();
    }
}
