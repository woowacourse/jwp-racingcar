package racingcar.domain;

import java.util.Objects;

public class Name {

    public static final int MIN_LENGTH = 5;

    private final String name;

    private Name(final String name) {
        validate(name);
        this.name = name;
    }

    public Name(final Name name) {
        this.name = name.getName();
    }

    public static Name of(final String name) {
        return new Name(name);
    }

    private void validate(final String name) {
        if (name.isBlank() || name.length() > MIN_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 %d글자 이하여야 합니다.", MIN_LENGTH));
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name other = (Name) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
