package racingcar.domain;

import java.util.Objects;

public final class Car {

    private final Name name;
    private final Position position;
    private boolean isWinner;

    public Car(final Name name) {
        this(name, Position.create());
    }

    public Car(final Name name, final Position position) {
        this.name = name;
        this.position = position;
    }

    public Car(final Car car) {
        this(car, false);
    }

    public Car(final Car car, final boolean isWinner) {
        this.name = Name.of(car.getNameValue());
        this.position = new Position(car.getPosition());
        this.isWinner = isWinner;
    }

    public void move(final boolean movable) {
        if (movable) {
            position.move();
        }
    }

    public Position getPosition() {
        return position;
    }

    public int getPositionValue() {
        return position.getPosition();
    }

    public Name getName() {
        return name;
    }

    public String getNameValue() {
        return name.getName();
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(final boolean result) {
        this.isWinner = result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
