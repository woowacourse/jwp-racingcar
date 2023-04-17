package racingcar.dao;

import java.util.Optional;
import racingcar.entity.GameEntity;

public interface GameDao {
    Optional<Integer> saveAndGetId(final GameEntity game);
}
