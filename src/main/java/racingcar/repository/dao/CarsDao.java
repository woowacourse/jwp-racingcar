package racingcar.repository.dao;

import java.util.List;
import java.util.Map;
import racingcar.repository.dao.entity.CarEntity;

public interface CarsDao {

    void insert(Long id, List<CarEntity> cars);

    List<CarEntity> find(Long playRecordId);

    Map<Long, List<CarEntity>> findAllCarsOrderByPlayCreatedAtDesc();
}
