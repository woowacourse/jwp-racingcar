package racingcar.domain;

import java.util.Objects;

public class Car {
    private static final int MIN_POSITION = 0;
    private static final int MIN_POWER = 4;

    private final Name name;
    private int position = MIN_POSITION;

    public Car(final Name name) {
        this.name = name;
    }

    public static Car from(String name) {
        return new Car(new Name(name));
    }

    public void move(final int power) {
        if (power >= MIN_POWER) {
            position++;
        }
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
