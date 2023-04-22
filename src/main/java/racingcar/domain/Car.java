package racingcar.domain;

import racingcar.strategy.RacingNumberGenerator;
import racingcar.domain.vo.Name;
import racingcar.domain.vo.Position;

public class Car {

    private static final int MOVABLE_VALUE = 4;
    private static final int START_POSITION = 0;

    private final Name name;
    private Position position;

    private Car(final Name name, final Position position) {
        this.name = name;
        this.position = position;
    }

    public static Car createCar(final String name) {
        return new Car(new Name(name), new Position(START_POSITION));
    }

    public static Car of(final String name, final int position) {
        final Name carName = new Name(name);
        final Position carPosition = new Position(position);

        return new Car(carName, carPosition);
    }

    public void race(final RacingNumberGenerator generator) {
        if (isMovable(generator.generate())) {
            position = position.move();
        }
    }

    public int compareTo(final Car other) {
        return this.position.compareTo(other.position);
    }

    public boolean isSamePosition(final Car other) {
        return this.position.isSamePosition(other.position);
    }

    private boolean isMovable(final int value) {
        return value >= MOVABLE_VALUE;
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

}
