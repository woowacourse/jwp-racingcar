package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class CarDto {

    private final String name;
    private final int position;

    public CarDto(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public static List<CarDto> from(final List<Car> cars) {
        return cars.stream()
            .map(car -> new CarDto(car.getName(), car.getPosition()))
            .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "CarDto{" +
            "name='" + name + '\'' +
            ", position=" + position +
            '}';
    }
}
