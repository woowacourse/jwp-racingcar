package racingcar.dao;

import racingcar.domain.CarResult;

import java.util.List;

public class DummyCarResultDao implements CarResultDao {
    @Override
    public int save(CarResult carResult) {
        return 0;
    }

    @Override
    public CarResult findById(int id) {
        return null;
    }

    @Override
    public List<CarResult> findAllByPlayResultId(int playResultId) {
        return null;
    }
}
