package racingcar.dto;

import racingcar.domain.Car;

public class CarParam {

    private final String name;
    private final int position;

    public CarParam(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public CarParam(Car car) {
        this(car.getName(), car.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
