package racingcar.dao;

import java.util.List;

import racingcar.entity.GameEntity;

public interface GameDao {
    GameEntity insertRacingResult(GameEntity gameEntity);

    List<GameEntity> selectAllResults();
}
