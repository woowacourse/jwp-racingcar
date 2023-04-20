package racingcar.dto;

import racingcar.domain.Car;

public class CarDto implements PlayerResultDto {
    String name;
    int position;

    public CarDto(final Car car) {
        this.name = car.getName();
        this.position = car.getPosition();
    }

    public CarDto(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }
}
