package racingcar.domain.car;

import java.util.Objects;

public class Name {
    public static final int MAX_NAME_LENGTH = 5;
    private static final String EXCEPTION_CAR_NAME_LENGTH = "1글자 이상 5자 이하의 이름을 입력해주세요.";
    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        validateCarNameLength(value);
    }

    private void validateCarNameLength(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException(EXCEPTION_CAR_NAME_LENGTH);
        }

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(EXCEPTION_CAR_NAME_LENGTH);
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
