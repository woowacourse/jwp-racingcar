package racingcar.domain.dao;

import racingcar.domain.Car;
import racingcar.domain.dao.entity.CarEntity;

import java.util.List;

public interface CarDao {
    void saveAll(final Long raceResultId, final List<Car> cars);

    List<CarEntity> findAll(final Long resultId);
}
