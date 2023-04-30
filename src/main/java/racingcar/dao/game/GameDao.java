package racingcar.dao.game;

import java.util.List;
import racingcar.entity.GameEntity;

public interface GameDao {
    Long save(final int trialCount);

    List<GameEntity> findAll();
}
