package racingcar.repository.dao;

import java.util.List;
import racingcar.repository.entity.PlayerPositionEntity;

public interface PlayerPositionDao {

    long save(PlayerPositionEntity playerPositionEntity);

    List<PlayerPositionEntity> findByGameId(final long gameId);
}
