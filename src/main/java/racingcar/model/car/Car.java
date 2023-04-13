package racingcar.model.car;

import java.util.Objects;

public class Car {

    private final Name name;
    private int position;

    public Car(Name name) {
        this.name = name;
        this.position = 0;
    }

    public void move(boolean isMoveForward) {
        if (isMoveForward) {
            position++;
        }
    }

    public String getName() {
        return name.getValue();
    }

    public int getPosition() {
        return position;
    }

    public boolean isSame(int position) {
        return this.position == position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return position == car.position && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
