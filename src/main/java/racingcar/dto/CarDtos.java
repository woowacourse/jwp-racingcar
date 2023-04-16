package racingcar.dto;

import static java.util.stream.Collectors.toList;

import racingcar.domain.Cars;
import java.util.List;

public class CarDtos {

    private final List<CarDto> carDtos;

    private CarDtos(final List<CarDto> carDtos) {
        this.carDtos = carDtos;
    }

    public static List<CarDto> from(final Cars cars) {
        return cars.getCars()
                .stream()
                .map(CarDto::from)
                .collect(toList());
    }
}
