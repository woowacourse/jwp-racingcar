package racingcar.dao;

import java.util.List;
import racingcar.entity.CarEntity;

public class ConsoleCarDao implements CarDao {
    @Override
    public void saveAll(final List<CarEntity> cars) {
    }

    @Override
    public List<CarEntity> findAll() {
        return List.of();
    }
}
