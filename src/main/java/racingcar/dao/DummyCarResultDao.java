package racingcar.dao;

import racingcar.domain.CarResult;

import java.util.List;

public class DummyCarResultDao implements CarResultDao {
    @Override
    public long save(CarResult carResult) {
        return 0;
    }

    @Override
    public CarResult findById(long id) {
        return null;
    }

    @Override
    public List<CarResult> findAllByPlayResultId(long playResultId) {
        return null;
    }
}
