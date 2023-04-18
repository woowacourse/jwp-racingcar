package racingcar.repository.dao;

import java.util.List;
import racingcar.repository.entity.PositionEntity;

public interface PositionDao {

    long save(PositionEntity positionEntity);

    List<PositionEntity> findByGameId(final long gameId);
}
