package racingcar.dao;

import java.util.List;
import racingcar.dao.entity.CarEntity;
import racingcar.domain.car.Car;

public class ConsoleCarDao implements CarDao {

    @Override
    public void batchInsert(final List<CarEntity> carEntity) {
    }

    @Override
    public List<Car> selectAll(final int gameId) {
        return null;
    }

    @Override
    public List<String> selectWinners(final int gameId) {
        return null;
    }
}
