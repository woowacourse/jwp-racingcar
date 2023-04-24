package racingcar.domain;

import java.util.Objects;

final class CarName {

    private static final int MAX_LENGTH = 5;
    private static final String NAME_ERROR_MESSAGE = "이름의 길이가 초과되었습니다";
    private static final String NAME_NULL_MESSAGE = "이름이 null입니다.";
    private static final String NAME_BLANK_MESSAGE = "이름이 공백입니다.";

    private final String name;

    public CarName(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name == null) {
            throw new IllegalArgumentException(NAME_NULL_MESSAGE);
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_BLANK_MESSAGE);
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(NAME_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarName carName = (CarName) o;
        return Objects.equals(name, carName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
