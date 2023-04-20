package racingcar.domain;

import racingcar.exception.IllegalGameArgumentException;

import java.util.Objects;

public class Car {

    private static final int START_POSITION = 0;

    private final Name name;
    private int position;

    public Car(final String name) {
        this(name, START_POSITION);
    }

    public Car(final String name, final int position) {
        validatePosition(position);
        this.name = new Name(name);
        this.position = position;
    }

    public boolean isFartherThan(final Car other) {
        return position > other.position;
    }

    public boolean hasSamePositionWith(final Car other) {
        return position == other.position;
    }

    public void move(final MoveChance moveChance) {
        if (moveChance.isMovable()) {
            position++;
        }
    }

    private void validatePosition(final int position) {
        if (position < START_POSITION) {
            throw new IllegalGameArgumentException("[ERROR] 위치는 시작점보다 작으면 안됩니다.");
        }
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

        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position;
    }
}
