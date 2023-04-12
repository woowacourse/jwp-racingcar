package racingcar.dto;

import racingcar.domain.Car;

public class CarPositionDto {

    private final int status;
    private final String carName;

    public CarPositionDto(final Car car) {
        this(car.getPosition(), car.getCarName());
    }

    public CarPositionDto(final int status, final String carName) {
        this.status = status;
        this.carName = carName;
    }

    public int getStatus() {
        return status;
    }

    public String getCarName() {
        return carName;
    }
}
