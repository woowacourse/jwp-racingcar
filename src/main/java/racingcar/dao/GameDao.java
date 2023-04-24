package racingcar.dao;

import java.util.List;

import racingcar.entity.GameEntity;

public interface GameDao {
    GameEntity insertGame(GameEntity gameEntity);

    List<GameEntity> selectAllGames();
}
