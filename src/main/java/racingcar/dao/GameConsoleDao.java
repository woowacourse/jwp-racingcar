package racingcar.dao;

import racingcar.dao.entity.GameEntity;

import java.util.Collections;
import java.util.List;

public class GameConsoleDao implements GameDao {
    @Override
    public List<GameEntity> findAll() {
         return Collections.emptyList();
    }

    @Override
    public Long save(final int trialCount) {
        return null;
    }
}
