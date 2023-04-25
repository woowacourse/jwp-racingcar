package racingcar.dao;

import racingcar.entity.GameEntity;

import java.util.List;

public interface RacingGameDao {

    List<GameEntity> findAll();

    int saveGame(GameEntity gameResultEntity);

    GameEntity getRacingGameById(int gameId);

}
