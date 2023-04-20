package racingcar.domain.dao;

import java.util.List;
import racingcar.domain.dao.entity.CarEntity;

public class ConsoleCarDao implements CarDao {

    @Override
    public void saveAll(final Long raceResultId, final List<CarEntity> carEntities) {

    }

    @Override
    public List<CarEntity> findAll(final Long resultId) {
        return null;
    }
}
