package racingcar.dao;

import java.util.List;
import racingcar.entity.CarEntity;

public interface CarDao {
    void saveAll(final List<CarEntity> players);
}
