package racingcar.dao;

import racingcar.entity.CarEntity;

import java.util.List;

public interface CarDao {
    Long insert(final CarEntity carEntity);
    List<CarEntity> findByGameResultId(final Long gameResultId);
}
