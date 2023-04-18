package racingcar.domain;

import java.util.Objects;

public class Name {

    private static final String NO_NAME_EXISTS_MESSAGE = "[ERROR] 이름은 반드시 있어야 합니다.";
    private static final String INVALID_LENGTH_MESSAGE = "[ERROR] 이름은 5글자까지 가능합니다.";

    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(NO_NAME_EXISTS_MESSAGE);
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException(INVALID_LENGTH_MESSAGE);
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
        final Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
