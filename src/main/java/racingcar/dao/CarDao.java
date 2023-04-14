package racingcar.dao;

import java.util.List;
import racingcar.domain.Car;

public interface CarDao {
    void saveAll(final int gameId, final List<Car> cars);
}
