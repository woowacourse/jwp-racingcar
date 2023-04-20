package racingcar.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.entity.CarEntity;

public class CarConsoleDao implements CarDao {

    private final Map<Integer, Car> cars = new LinkedHashMap<>();
    private int id = 1;

    @Override
    public int insertCar(final CarEntity carEntity, final int gameId) {
        cars.put(id++, new Car(carEntity.getName(), carEntity.getPosition()));
        return id - 1;
    }

    @Override
    public List<CarEntity> findCars(final int gameId) {
        return cars.values().stream()
                .map(car -> CarEntity.of(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
