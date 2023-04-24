package racingcar.dto;

import racingcar.domain.Car;

public class CarForNameAndPosition {
    private final String name;
    private final int position;

    public CarForNameAndPosition(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public CarForNameAndPosition(Car car) {
        this(car.getName(), car.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
