package racingcar.dto;

import racingcar.domain.Car;

public final class CarDto {

    private final String name;
    private final int position;

    public CarDto(final Car car) {
        this.name = car.getCarName();
        this.position = car.getPosition();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
