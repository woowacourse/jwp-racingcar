package racingcar.dao;

import java.util.List;
import racingcar.dao.entity.CarEntity;
import racingcar.domain.car.Car;

public interface CarDao {

    void batchInsert(final List<CarEntity> carEntity);

    List<Car> selectAll(final int gameId);

    List<String> selectWinners(final int gameId);
}
