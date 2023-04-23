package racingcar.dao;

import racingcar.entity.CarEntity;

import java.util.List;

public interface RacingCarDao {

    List<CarEntity> findCarsByGameId(int id);

    void saveCar(CarEntity carEntity);

    int findIdByName(String name);

}
