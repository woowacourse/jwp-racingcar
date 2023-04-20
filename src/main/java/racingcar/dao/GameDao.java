package racingcar.dao;

import java.util.List;
import racingcar.entity.GameEntity;

public interface GameDao {

    Long insert(GameEntity gameEntity);

    List<GameEntity> findAll();
}
