package racingcar.dao;

import java.util.List;
import racingcar.entity.GameEntity;

public interface GameDao {
    int insertGame(final int tryTimes);
    List<GameEntity> findAll();
}
