package racingcar.dao;

import racingcar.domain.CarResult;

import java.util.List;

public interface CarResultDao {
    int save(CarResult carResult);

    CarResult findById(int id);

    List<CarResult> findAllByPlayResultId(int playResultId);
}
