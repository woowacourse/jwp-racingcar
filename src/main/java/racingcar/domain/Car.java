package racingcar.domain;

import java.util.Random;

public class Car extends Vehicle {

    private static final int RANDOM_MOVE_BOUNDARY = 4;
    private static final int RANDOM_NUMBER_GENERATE_RANGE = 10;
    private static final int START_POSITION = 0;
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;

    public Car(String name) {
        super(name, START_POSITION);
        validateLength(name);
    }

    private void validateLength(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 최소 " + MIN_NAME_LENGTH + "에서 최대 " + MAX_NAME_LENGTH + "글자입니다.");
        }
    }

    @Override
    public boolean isMove() {
        return new Random().nextInt(RANDOM_NUMBER_GENERATE_RANGE) >= RANDOM_MOVE_BOUNDARY;
    }

    @Override
    public boolean equals(final Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
