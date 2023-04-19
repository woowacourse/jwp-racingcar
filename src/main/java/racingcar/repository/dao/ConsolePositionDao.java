package racingcar.repository.dao;

import java.util.Collections;
import java.util.List;

import racingcar.repository.entity.PositionEntity;

public class ConsolePositionDao implements PositionDao {

    @Override
    public long save(final PositionEntity positionEntity) {
        return 0;
    }

    @Override
    public List<PositionEntity> findByGameId(final long gameId) {
        return Collections.emptyList();
    }
}
