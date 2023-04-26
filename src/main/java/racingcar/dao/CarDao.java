package racingcar.dao;

import java.util.List;
import racingcar.dao.entity.CarEntity;

public interface CarDao {

    void saveAll(List<CarEntity> racingCars);

    List<CarEntity> findByRacingGameId(Long id);
}
