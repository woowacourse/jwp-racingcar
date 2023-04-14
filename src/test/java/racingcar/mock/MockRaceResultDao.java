package racingcar.mock;

import racingcar.domain.dao.RaceResultDao;
import racingcar.domain.dao.entity.RaceEntity;

import java.util.Collections;
import java.util.List;

public class MockRaceResultDao implements RaceResultDao {
    @Override
    public Long save(final int trialCount, final String winners) {
        return null;
    }

    @Override
    public List<RaceEntity> findAll() {
        return Collections.emptyList();
    }
}
