package racingcar.dao;

import java.util.List;
import racingcar.domain.entity.CarEntity;

public interface CarDao {

    void save(int gameId, List<CarEntity> carResultDtos);

    List<CarEntity> findAll();

    List<CarEntity> findByGameId(int id);
}
