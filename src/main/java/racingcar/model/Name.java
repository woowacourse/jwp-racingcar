package racingcar.model;

import racingcar.constant.ExceptionMessage;

public class Name {
    private static final int CAR_MAX_NAME_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(String name) {
        if (name.isEmpty() || name.length() > CAR_MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ExceptionMessage.EXCEPTION_MESSAGE.getExceptionMessage());
        }
    }

    public String getName() {
        return this.name;
    }
}
