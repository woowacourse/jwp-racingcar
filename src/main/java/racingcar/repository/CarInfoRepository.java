package racingcar.repository;

import racingcar.domain.entity.CarInfo;

import java.util.List;
import java.util.Optional;

public interface CarInfoRepository {
    Optional<Integer> saveCar(CarInfo carInfo);

    List<CarInfo> findAllByRaceId(int raceId);
}
