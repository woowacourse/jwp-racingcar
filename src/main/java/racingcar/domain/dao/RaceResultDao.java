package racingcar.domain.dao;

import racingcar.domain.dao.entity.RaceEntity;

import java.util.List;

public interface RaceResultDao {

    Long save(final int trialCount, final String winners);

    List<RaceEntity> findAll();
}
