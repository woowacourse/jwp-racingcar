package racingcar.dao;

import java.util.Optional;
import racingcar.dao.entity.GameEntity;

public interface GameDao {

    Long insert(final GameEntity gameEntity);

    Optional<Integer> countGames();
}
