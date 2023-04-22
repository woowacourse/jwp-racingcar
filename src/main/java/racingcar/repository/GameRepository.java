package racingcar.repository;

import racingcar.entity.GameEntity;

import java.util.List;

public interface GameRepository {
    GameEntity save(GameEntity entity);

    GameEntity findById(long id);

    List<GameEntity> findAll();
}
