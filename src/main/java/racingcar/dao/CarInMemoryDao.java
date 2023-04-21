package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import racingcar.dao.entity.CarEntity;

public class CarInMemoryDao implements CarDao {
    private final Map<Long, CarEntity> database = new HashMap<>();
    private Long id = 1L;

    @Override
    public void saveAll(List<CarEntity> racingCars) {
        for (CarEntity racingCar : racingCars) {
            database.put(id, racingCar);
            id++;
        }
    }

    @Override
    public List<CarEntity> findByRacingGameId(Long id) {
        return database.values().stream()
                .filter(carEntity -> Objects.equals(carEntity.getRacingGameId(), id))
                .collect(Collectors.toList());
    }
}
