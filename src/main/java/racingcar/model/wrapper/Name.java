package racingcar.model.wrapper;

import racingcar.exception.BadRequestException;
import racingcar.exception.ExceptionMessage;

public class Name {

    private static final int NAME_MIN_LENGTH = 1;
    private static final int NAME_MAX_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new BadRequestException(ExceptionMessage.BLANK_NAME);
        }
        if (!validateLength(name)) {
            throw new BadRequestException(ExceptionMessage.WRONG_NAME_LENGTH);
        }
    }

    private boolean validateLength(String name) {
        int length = name.length();

        return length >= NAME_MIN_LENGTH && length <= NAME_MAX_LENGTH;
    }

    public String getName() {
        return name;
    }
}
