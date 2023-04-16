package racingcar.model;

import java.util.Objects;
import java.util.Random;

public class Car {

    public static final int RANDOM_MOVE_BOUNDARY = 4;
    public static final int RANDOM_NUMBER_GENERATE_RANGE = 10;
    public static final int START_POSITION = 0;
    private static final int LENGTH_LOWER_BOUND = 1;
    private static final int LENGTH_UPPER_BOUND = 5;
    private static final String EXCEEDED_CAR_NAME_LENGTH_ERROR = "자동차명은 1 ~ 5 글자로 입력해야합니다.";

    private final String name;
    private int distance;

    public Car(String name) {
        validateNameLength(name);
        validateNamesIsEmpty(name);
        this.name = name;
        this.distance = START_POSITION;
    }

    private void validateNameLength(String name) {
        if (LENGTH_LOWER_BOUND > name.length() || name.length() > LENGTH_UPPER_BOUND) {
            throw new IllegalArgumentException(EXCEEDED_CAR_NAME_LENGTH_ERROR);
        }
    }

    private void validateNamesIsEmpty(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(EXCEEDED_CAR_NAME_LENGTH_ERROR);
        }
    }

    public int getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public void move() {
        distance++;
    }

    public boolean isMove() {
        return new Random().nextInt(RANDOM_NUMBER_GENERATE_RANGE) >= RANDOM_MOVE_BOUNDARY;
    }

    @Override
    public boolean equals(final Object o) {
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
