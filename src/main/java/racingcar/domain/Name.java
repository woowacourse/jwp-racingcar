package racingcar.domain;

import static racingcar.utils.ErrorMessage.WRONG_NAME_CONTAINS_BLANK;
import static racingcar.utils.ErrorMessage.WRONG_NAME_LENGTH;

public class Name {

    private final int MIN_NAME_SIZE = 0;
    private final int MAX_NAME_SIZE = 5;

    private final String name;

    public Name(String name) {
        validateContainsBlank(name);
        validateCarNameLength(name.length());
        this.name = name;
    }

    private void validateCarNameLength(int length) {
        if (length <= MIN_NAME_SIZE || length > MAX_NAME_SIZE) {
            throw new IllegalArgumentException(WRONG_NAME_LENGTH.of());
        }
    }

    private void validateContainsBlank(String carNames) {
        if (carNames.contains(" ")) {
            throw new IllegalArgumentException(WRONG_NAME_CONTAINS_BLANK.of());
        }
    }

    String name() {
        return name;
    }
}
