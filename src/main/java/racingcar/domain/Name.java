package racingcar.domain;

import racingcar.exception.ExceptionMessage;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.isBlank() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessage.INVALID_NAME_LENGTH.getValue(), MAX_NAME_LENGTH)
            );
        }
    }

    public String getName() {
        return name;
    }
}
