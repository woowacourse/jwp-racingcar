package racingcar.controller.dto;

import racingcar.domain.Car;


public class CarResponse {
    private String name;
    private int position;

    public CarResponse() {

    }

    private CarResponse(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static CarResponse of(Car car) {
        String name = car.getName();
        int position = car.getDrivenDistance();
        return new CarResponse(name, position);
    }

    public static CarResponse of(String name, int position) {
        return new CarResponse(name, position);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
