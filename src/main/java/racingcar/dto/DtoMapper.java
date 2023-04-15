package racingcar.dto;

import racingcar.domain.car.Car;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    public static List<CarDto> toCarDtos(List<Car> cars) {
        return cars.stream()
                   .map(car -> new CarDto(car.getName(), car.getPosition()))
                   .collect(Collectors.toUnmodifiableList());
    }
}
