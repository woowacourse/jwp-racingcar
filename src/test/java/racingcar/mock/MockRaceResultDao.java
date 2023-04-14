package racingcar.mock;

import racingcar.domain.dao.RaceResultDao;
import racingcar.domain.dao.entity.RaceEntity;

import java.util.List;

public class MockRaceResultDao implements RaceResultDao {
    @Override
    public Long save(final int trialCount, final String winners) {
        return 1L;
    }

    @Override
    public List<RaceEntity> findAll() {
        final RaceEntity raceEntity = new RaceEntity(1L, 10, "pobi");
        return List.of(raceEntity);
    }
}
