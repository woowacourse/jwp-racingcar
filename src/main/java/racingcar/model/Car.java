package racingcar.model;

import racingcar.strategy.RacingNumberGenerator;

import java.util.Objects;

public class Car {

    private final Name name;
    private final Position position;

    public Car(final String name) {
        this.name = new Name(name);
        this.position = new Position(0);
    }

    public Car(final String name, final int position) {
        this.name = new Name(name);
        this.position = new Position(position);
    }

    public void race(RacingNumberGenerator generator) {
        position.move(generator);
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }

    public int compareTo(Car otherCar) {
        return this.position.compareTo(otherCar.position);
    }

    public boolean isSamePosition(Car otherCar) {
        return this.position.isSamePosition(otherCar.position);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Car car = (Car) o;
        return Objects.equals(name, car.name) && Objects.equals(position, car.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
