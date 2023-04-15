package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.CarResponseDto;

public class CarDtoBuilder {

    public static List<CarDto> dtos(List<Car> cars, List<String> winnerNames) {
        return cars.stream()
                .map(car -> CarDto.from(car, winnerNames))
                .collect(Collectors.toList());
    }

    public static List<CarResponseDto> responseDtos(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarResponseDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    public static List<CarResponseDto> from(List<CarDto> carDtos) {
        return carDtos.stream()
                .map(car -> new CarResponseDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
