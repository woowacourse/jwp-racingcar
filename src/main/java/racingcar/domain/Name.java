package racingcar.domain;

import racingcar.error.ErrorMessage;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name from(String name) {
        validateLength(name);

        return new Name(name);
    }

    private static void validateLength(String name) {
        if (name.isBlank() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.INVALID_NAME_LENGTH.getValue(), MAX_NAME_LENGTH)
            );
        }
    }

    public String getName() {
        return name;
    }
}
