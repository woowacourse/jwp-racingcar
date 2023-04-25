package racingcar.dao;

import racingcar.dao.entity.CarEntity;

import java.util.List;

public interface CarRepository {

    void saveAll(List<CarEntity> carEntities);

    List<CarEntity> findCarsByGameID(final int gameId);
}
