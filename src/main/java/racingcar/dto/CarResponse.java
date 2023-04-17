package racingcar.dto;

import racingcar.domain.Car;

public class CarResponse {

    private final String name;
    private final int position;

    private CarResponse(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static CarResponse from(Car car) {
        String name = car.getName();
        int position = car.getDrivenDistance();
        return new CarResponse(name, position);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
