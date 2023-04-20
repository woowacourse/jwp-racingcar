package racingcar.domain.dao;

import java.util.List;
import racingcar.domain.dao.entity.CarEntity;

public interface CarDao {

    void saveAll(final Long raceResultId, final List<CarEntity> carEntities);

    List<CarEntity> findAll(final Long resultId);
}
