package racingcar.dao;

import racingcar.entity.CarEntity;

import java.util.List;

public interface CarDao {
    void insert(List<CarEntity> carEntities);

    List<CarEntity> findByGameResultId(final Long gameResultId);
}
