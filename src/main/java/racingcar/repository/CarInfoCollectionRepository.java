package racingcar.repository;

import racingcar.domain.entity.CarInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarInfoCollectionRepository implements CarInfoRepository {
    private final List<CarInfo> cars = new ArrayList<>();
    private int idHolder = 0;

    @Override
    public Optional<Integer> saveCar(CarInfo carInfo) {
        int id = idHolder++;
        cars.add(
                new CarInfo(id,
                        carInfo.getRacingId(),
                        carInfo.getName(),
                        carInfo.getPosition(),
                        carInfo.getIsWinner())
        );
        if (id < 0) {
            return Optional.empty();
        }
        return Optional.of(id);
    }

    @Override
    public List<CarInfo> findAllByRaceId(int raceId) {
        return cars.stream()
                .filter(carInfo -> carInfo.getRacingId() == raceId)
                .collect(Collectors.toList());
    }
}
