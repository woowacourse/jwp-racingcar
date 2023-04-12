package racingcar.repository.dao;

import java.util.List;
import racingcar.repository.entity.GameUsersPositionEntity;

public interface GameUsersPositionDao {

    long save(GameUsersPositionEntity gameUsersPositionEntity);

    List<GameUsersPositionEntity> findByGameId(final long gameId);
}
