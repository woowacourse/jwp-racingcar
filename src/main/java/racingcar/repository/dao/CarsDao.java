package racingcar.repository.dao;

import java.util.List;
import java.util.Map;
import racingcar.repository.dao.entity.CarEntity;

public interface CarsDao {

    void insert(long id, List<CarEntity> cars);

    List<CarEntity> find(long id);

    Map<Long, List<CarEntity>> findAllByPlayerId();
}
