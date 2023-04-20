package racingcar.dao;

import java.util.List;
import racingcar.entity.CarEntity;

public interface CarDao {
    int insertCar(final CarEntity carEntity, final int gameId);
    List<CarEntity> findCars(final int gameId);
}
