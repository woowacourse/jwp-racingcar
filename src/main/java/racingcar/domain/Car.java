package racingcar.domain;

import racingcar.domain.vo.CarName;
import racingcar.domain.vo.Position;

public class Car {

    public static final int MIN_REQUIRED_POWER = 3;
    private final CarName name;
    private Position position;

    private Car(CarName name) {
        this.name = name;
        this.position = Position.of(0);
    }

    public static Car of(CarName name) {
        return new Car(name);
    }

    public String getName() {
        return name.getValue();
    }

    public int getPosition() {
        return position.getValue();
    }

    public void move(int power) {
        if (power > MIN_REQUIRED_POWER) {
            position = position.plus();
        }
    }

    public boolean hasPosition(int position) {
        return this.position.isValueOf(position);
    }

    @Override
    public String toString() {
        return "Car{" + "name=" + name + ", position=" + position + '}';
    }
}
