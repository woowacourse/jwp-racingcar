package racingcar.mapper;

import racingcar.entity.CarEntity;

import java.util.List;

public interface CarMapper {
    CarEntity save(CarEntity entity);

    CarEntity findById(long id);

    List<CarEntity> findAllByGameId(long id);

    List<CarEntity> findWinnersByGameId(long id);
}
