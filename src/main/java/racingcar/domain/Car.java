package racingcar.domain;

import java.util.Objects;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MIN_POSITION = 0;
    private static final int MIN_POWER = 4;

    private final String name;
    private int position = MIN_POSITION;

    public Car(final String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 5글자를 초과할 수 없습니다.\n" + "Name : " + name);
        }
        if (name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 1글자 미만일 수 없습니다.\n" + "Name : " + name);
        }
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
        return name;
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
