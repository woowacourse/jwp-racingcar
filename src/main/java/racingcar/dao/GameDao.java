package racingcar.dao;

import racingcar.dao.entity.GameEntity;

import java.util.List;

public interface GameDao {
    List<GameEntity> findAll();
    Long save(final int trialCount);
}
