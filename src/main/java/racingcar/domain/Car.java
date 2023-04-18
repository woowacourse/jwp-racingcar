package racingcar.domain;

import racingcar.utils.RacingNumberGenerator;

public class Car {

    private final Name name;
    private final Position position;

    public Car(String name) {
        this.name = new Name(name);
        this.position = new Position();
    }

    public void race(RacingNumberGenerator generator) {
        position.move(generator);
    }

    public int compareTo(Car otherCar) {
        return this.position.compareTo(otherCar.position);
    }

    public boolean isSamePosition(Car otherCar) {
        return this.position.isSamePosition(otherCar.position);
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }
}
