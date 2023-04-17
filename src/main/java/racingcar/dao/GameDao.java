package racingcar.dao;

import racingcar.dao.entity.GameEntity;

import java.util.List;

public interface GameDao {
    int save(int trialCount);

    List<GameEntity> findAll();
}
