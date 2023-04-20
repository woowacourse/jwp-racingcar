package racingcar.dto;

import racingcar.domain.Car;

public class CarResponse {

    private final String name;
    private final int position;

    public CarResponse(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public CarResponse(Car car) {
        this(car.getName(), car.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
