package racingcar.dto;

import racingcar.domain.Car;

public class CarForSave {
    private final String name;
    private final int position;

    public CarForSave(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public CarForSave(Car car) {
        this(car.getName(), car.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
