package racingcar.dto;

import racingcar.entity.CarEntity;
import racingcar.model.car.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarDto {

    private final String name;
    private final int position;

    public CarDto(String name, int count) {
        this.name = name;
        this.position = count;
    }

    public static CarDto fromDomain(Car car) {
        return new CarDto(car.getName(), car.getPosition());
    }

    public static List<CarDto> fromDomain(List<Car> cars) {
        return cars.stream()
                .map(CarDto::fromDomain)
                .collect(Collectors.toList());
    }

    public static CarDto fromEntity(CarEntity carEntity) {
        return new CarDto(carEntity.getName(), carEntity.getPosition());
    }

    public static List<CarDto> fromEntity(List<CarEntity> carEntities) {
        return carEntities.stream()
                .map(CarDto::fromEntity)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
