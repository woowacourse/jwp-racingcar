package racingcar.dao;

import racingcar.entity.GameEntity;

import java.util.List;

public interface GameDao {

    long insert(int moveCount);

    List<GameEntity> selectAll();

}
