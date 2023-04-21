package racingcar.dao;

import java.util.List;

import racingcar.service.CarEntity;

public interface CarDao {
    List<CarEntity> selectCarsByGameId(int ragingResultId);

    void insertCar(final CarEntity carEntity);
}
