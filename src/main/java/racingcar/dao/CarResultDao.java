package racingcar.dao;

import racingcar.domain.CarResult;

import java.util.List;

public interface CarResultDao {
    long save(CarResult carResult);

    CarResult findById(long id);

    List<CarResult> findAllByPlayResultId(long playResultId);
}
