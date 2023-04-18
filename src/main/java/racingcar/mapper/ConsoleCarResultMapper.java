package racingcar.mapper;

import racingcar.entity.CarResultEntity;

import java.util.List;

public class ConsoleCarResultMapper implements CarResultMapper {
    @Override
    public long save(CarResultEntity entity) {
        return 0;
    }

    @Override
    public CarResultEntity findById(long id) {
        return null;
    }

    @Override
    public List<CarResultEntity> findAllByPlayResultId(long id) {
        return null;
    }
}
