package racingcar.dao;

import java.util.List;

import racingcar.entity.CarEntity;

public interface CarDao {
    List<CarEntity> selectCarsByGameId(int gameId);

    void insertCar(final CarEntity carEntity);
}
