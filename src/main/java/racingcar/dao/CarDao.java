package racingcar.dao;

import java.util.List;
import racingcar.domain.entity.CarResultEntity;

public interface CarDao {

    void saveAll(int gameId, List<CarResultEntity> carEntities);

    List<CarResultEntity> findAll();
}
