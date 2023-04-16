package racingcar.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final Pattern ENG_KOR_REGEX = Pattern.compile("^[가-힣a-zA-Z]+$");
    private static final int MAX_NAME_LENGTH = 5;
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateBlank(name);
        validateNameLength(name);
        validateInvalidName(name);
    }

    private void validateBlank(String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR]: 자동차 이름은 공백을 입력할 수 없습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR]: 자동차 이름은 5자 이하여야 합니다.");
        }
    }

    private void validateInvalidName(String name) {
        if (!ENG_KOR_REGEX.matcher(name).matches()) {
            throw new IllegalArgumentException("[ERROR]: 자동차 이름은 영문 혹은 한글만 가능합니다.");
        }
    }

    public String getValue() {
        return name;
    }
}
