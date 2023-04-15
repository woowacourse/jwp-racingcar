package racingcar.domain.dao;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.dao.entity.CarEntity;

public interface CarDao {

    void saveAll(final Long raceResultId, final List<Car> cars);

    List<CarEntity> findAll(final Long resultId);
}
