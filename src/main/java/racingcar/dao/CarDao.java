package racingcar.dao;

import java.util.List;
import racingcar.domain.entity.CarEntity;

public interface CarDao {

    void saveAll(final int gameId, final List<CarEntity> carEntities);

    List<CarEntity> findAll();
}
