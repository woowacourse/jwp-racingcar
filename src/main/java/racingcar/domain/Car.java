package racingcar.domain;

import java.util.Objects;
import java.util.Random;

public class Car implements MovableStrategy {

    private static final int RANDOM_MOVE_BOUNDARY = 4;
    private static final int RANDOM_NUMBER_GENERATE_RANGE = 10;
    private static final int START_POSITION = 0;
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private int distance;

    public Car(final String name) {
        validateLength(name);
        this.name = name;
        this.distance = START_POSITION;
    }

    private void validateLength(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 최소 " + MIN_NAME_LENGTH + "에서 최대 " + MAX_NAME_LENGTH + "글자입니다.");
        }
    }

    public void move() {
        this.distance++;
    }

    public boolean isSameDistance(final int distance) {
        return this.distance == distance;
    }

    @Override
    public boolean canMove() {
        return new Random().nextInt(RANDOM_NUMBER_GENERATE_RANGE) >= RANDOM_MOVE_BOUNDARY;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return distance == car.distance && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, distance);
    }
}
