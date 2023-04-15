package racingcar.dao;

import racingcar.dto.CarEntity;

import java.util.List;

public interface CarDao {

    void save(int gameId, List<CarEntity> carResultDtos);

    List<CarEntity> findAll();
}
