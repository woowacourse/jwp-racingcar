package racingcar.domain.dao;

import java.util.List;
import racingcar.domain.dao.entity.RaceEntity;

public interface RaceResultDao {

    Long save(final int trialCount, final String winners);

    List<RaceEntity> findAll();
}
