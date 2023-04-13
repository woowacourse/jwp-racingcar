package racingcar.dao;

import racingcar.domain.Car;

import java.util.List;

public interface CarDao {
    void saveAll(final int gameId, final List<Car> cars);
}
