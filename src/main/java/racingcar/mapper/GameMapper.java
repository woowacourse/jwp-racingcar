package racingcar.mapper;

import racingcar.entity.GameEntity;

import java.util.List;

public interface GameMapper {
    GameEntity save(GameEntity entity);

    GameEntity findById(long id);

    List<GameEntity> findAll();
}
