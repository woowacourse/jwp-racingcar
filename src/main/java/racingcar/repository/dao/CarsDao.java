package racingcar.repository.dao;

import java.util.List;
import racingcar.repository.dao.entity.CarEntity;

public interface CarsDao {

    void insert(List<CarEntity> cars);

    List<CarEntity> find(Long playRecordId);

    List<List<CarEntity>> findAllCarsOrderByPlayCreatedAtDesc();
}
