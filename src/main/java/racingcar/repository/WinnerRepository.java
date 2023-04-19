package racingcar.repository;

import racingcar.entity.CarEntity;

public interface WinnerRepository {
    CarEntity save(CarEntity entity);
}
