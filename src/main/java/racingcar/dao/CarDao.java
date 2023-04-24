package racingcar.dao;

import racingcar.entity.CarEntity;

import java.util.List;

public interface CarDao {

    void insert(List<CarEntity> carEntities, long gameId);

    List<CarEntity> selectByGameId(long gameId);

}
