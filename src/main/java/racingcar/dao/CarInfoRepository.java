package racingcar.dao;

import racingcar.entity.CarInfo;

import java.util.List;

public interface CarInfoRepository {
    int saveCar(CarInfo carInfo);

    List<CarInfo> findAllByRaceId(int raceId);
}
