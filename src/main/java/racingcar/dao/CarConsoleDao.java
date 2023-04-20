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

    @Override
    public CarDto findCar(String name, int gameId) {
        Integer carId = findCarByName(name);
        Car car = cars.get(carId);
        return CarDto.of(car.getName(), car.getPosition());
    }

    private Integer findCarByName(String name) {
        return cars.entrySet().stream()
                .filter(carEntry -> carEntry.getValue().getName().equals(name))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(name + " 자동차를 찾을 수 없습니다."))
                .getKey();
    }
}
