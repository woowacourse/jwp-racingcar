package racingcar.dao;

import java.util.List;
import racingcar.domain.entity.CarEntity;

public interface CarDao {

    void saveAll(int gameId, List<CarEntity> carEntities);

    List<CarEntity> findAll();
}
