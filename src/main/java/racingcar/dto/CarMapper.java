package racingcar.dto;

import racingcar.model.car.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {

    public static CarDto toCarDto(Car car) {
        return new CarDto(car.getName(), car.getPosition());
    }

    public static List<CarDto> toCarDtos(List<Car> cars) {
        return cars.stream()
                .map(CarMapper::toCarDto)
                .collect(Collectors.toList());
    }

    public static Car toCar(CarDto carDto) {
        return new Car(carDto.getName(), carDto.getPosition());
    }

}
