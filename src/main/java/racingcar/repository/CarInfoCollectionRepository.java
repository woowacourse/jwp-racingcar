package racingcar.repository;

import racingcar.domain.entity.CarInfo;

import java.util.*;

public class CarInfoCollectionRepository implements CarInfoRepository {
    private final Map<Integer, List<CarInfo>> cars = new HashMap<>();
    private int idHolder = 0;

    @Override
    public Optional<Integer> saveCar(CarInfo carInfo) {
        int id = idHolder++;
        if (id < 0) {
            return Optional.empty();
        }

        List<CarInfo> cars = this.cars.getOrDefault(id, new ArrayList<>());
        cars.add(
                new CarInfo(id,
                        carInfo.getRacingId(),
                        carInfo.getName(),
                        carInfo.getPosition(),
                        carInfo.getIsWinner())
        );

        this.cars.put(id, cars);
        return Optional.of(id);
    }

    @Override
    public List<CarInfo> findAllByRaceId(int raceId) {
        return cars.getOrDefault(raceId, Collections.emptyList());
    }
}
