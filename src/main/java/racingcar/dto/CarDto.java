package racingcar.dto;

import racingcar.domain.Car;

public class CarDto {
    private final String name;
    private final int position;

    private CarDto(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public static CarDto from(Car car) {
        return new CarDto(car.name(), car.position());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
