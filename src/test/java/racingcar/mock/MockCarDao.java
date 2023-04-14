package racingcar.mock;

import racingcar.domain.Car;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.entity.CarEntity;

import java.util.Collections;
import java.util.List;

public class MockCarDao implements CarDao {
    @Override
    public void saveAll(final Long raceResultId, final List<Car> cars) {
    }

    @Override
    public List<CarEntity> findAll(final Long resultId) {
        return Collections.emptyList();
    }
}
