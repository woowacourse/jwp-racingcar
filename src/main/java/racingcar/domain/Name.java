package racingcar.domain;

import java.util.Objects;

public final class Name {
    private static final int MIN_LENGTH_NAME = 1;
    private static final int MAX_LENGTH_NAME = 5;

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (isLengthNotOk(value)) {
            throw new IllegalArgumentException("[ERROR] 자동차이름의 길이는 1-5자까지 가능합니다.");
        }
    }

    private boolean isLengthNotOk(final String name) {
        return !(MIN_LENGTH_NAME <= name.length() && name.length() <= MAX_LENGTH_NAME);
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
        Name name1 = (Name) o;
        return Objects.equals(getValue(), name1.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
