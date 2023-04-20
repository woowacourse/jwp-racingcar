package racingcar.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.dto.CarDto;

public class CarConsoleDao implements CarDao {

    private final Map<Integer, Car> cars = new LinkedHashMap<>();
    private int id = 1;

    @Override
    public int insertCar(CarDto carDto, int gameId) {
        cars.put(id++, new Car(carDto.getName(), carDto.getPosition()));
        return id - 1;
    }

    @Override
    public List<CarDto> findCars(int gameId) {
        return cars.values().stream()
                .map(car -> CarDto.of(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
