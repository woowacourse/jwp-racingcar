package racingcar.dao;

import racingcar.entity.CarInfo;

public interface CarInfoRepository {
    int saveCar(CarInfo carInfo);
}
