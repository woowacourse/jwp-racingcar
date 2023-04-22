package racingcar.dto;

import racingcar.domain.Car;
import racingcar.domain.entity.CarInfo;

public class CarDto {
    private final String name;
    private final int position;

    public CarDto(Car car) {
        this.name = car.getName();
        this.position = car.getPosition();
    }

    public CarDto(CarInfo carInfo) {
        this.name = carInfo.getName();
        this.position = carInfo.getPosition();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
