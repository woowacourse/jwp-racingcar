package racingcar.repository;

import racingcar.entity.CarEntity;

import java.util.List;

public interface CarRepository {
    CarEntity save(CarEntity entity);

    CarEntity findById(long id);

    List<CarEntity> findAllByGameId(long id);
}
