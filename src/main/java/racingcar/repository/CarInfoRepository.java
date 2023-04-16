package racingcar.repository;

import racingcar.domain.entity.CarInfo;

import java.util.List;

public interface CarInfoRepository {
    int saveCar(CarInfo carInfo);

    List<CarInfo> findAllByRaceId(int raceId);
}
