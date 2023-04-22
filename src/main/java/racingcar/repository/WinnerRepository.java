package racingcar.repository;

import racingcar.entity.CarEntity;

import java.util.List;

public interface WinnerRepository {
    CarEntity save(CarEntity entity);

    List<CarEntity> findWinnersByGameId(long id);
}
