package racingcar.dao;

import racingcar.entity.GameEntity;

import java.util.List;

interface GameDao {

    Long save(final GameEntity gameEntity);

    List<GameEntity> findAll();
}
