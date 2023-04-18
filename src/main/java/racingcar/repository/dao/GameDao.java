package racingcar.repository.dao;

import java.util.List;
import racingcar.repository.entity.GameEntity;

public interface GameDao {

    long save(final GameEntity gameEntity);

    List<GameEntity> findAll();
}
