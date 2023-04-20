package racingcar.domain;

import static racingcar.utils.ErrorMessage.WRONG_NAME_CONTAINS_BLANK;
import static racingcar.utils.ErrorMessage.WRONG_NAME_LENGTH;

public class Car {

    private final int MOVING_STANDARD_NUM = 4;
    private final int ADD_POINT = 1;

    private final Name name;
    private int position;

    public Car(final String name) {
        this(name, 0);
    }

    public Car(final String name, final int position) {
        this.name = new Name(name);
        this.position = position;
    }

    public void move(final int number) {
        if (isAllowedToMove(number)) {
            this.position += ADD_POINT;
        }
    }

    private boolean isAllowedToMove(final int number) {
        return number >= MOVING_STANDARD_NUM;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name.getName();
    }

    private static class Name {

        private final int MIN_NAME_SIZE = 0;
        private final int MAX_NAME_SIZE = 5;

        private final String name;

        private Name(final String name) {
            validateContainsBlank(name);
            validateCarNameLength(name.length());
            this.name = name;
        }

        private void validateCarNameLength(final int length) {
            if (length <= MIN_NAME_SIZE || length > MAX_NAME_SIZE) {
                throw new IllegalArgumentException(WRONG_NAME_LENGTH.of());
            }
        }

        private void validateContainsBlank(final String carNames) {
            if (carNames.contains(" ")) {
                throw new IllegalArgumentException(WRONG_NAME_CONTAINS_BLANK.of());
            }
        }

        private String getName() {
            return name;
        }
    }
}
