package racingcar.dao;

import racingcar.domain.CarResult;

import java.util.List;

public interface CarResultDao {
    long save(CarResult carResult);

    void saveAll(List<CarResult> carResults);

    CarResult findById(long id);

    List<CarResult> findAllByPlayResultId(long playResultId);
}
