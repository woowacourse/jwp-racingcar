package racingcar.mapper;

import racingcar.entity.CarResultEntity;

import java.util.List;

public interface CarResultMapper {
    long save(CarResultEntity entity);

    CarResultEntity findById(long id);

    List<CarResultEntity> findAllByPlayResultId(long id);
}
