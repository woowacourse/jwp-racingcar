package racingcar.mock;

import racingcar.domain.Car;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.entity.CarEntity;

import java.util.List;

public class MockCarDao implements CarDao {
    @Override
    public void saveAll(final Long raceResultId, final List<Car> cars) {
    }

    @Override
    public List<CarEntity> findAll(final Long resultId) {
        return List.of(new CarEntity(1L, "pobi", 10),
                new CarEntity(2L, "crong", 5),
                new CarEntity(3L, "honux", 3));
    }
}
