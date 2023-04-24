package racingcar.domain;

import java.util.Objects;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_NAME_LENGTH = 1;

    private final String name;

    public Name(String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(final String name) {
        validateOverMaxLength(name);
        validateUnderMinLength(name);
    }

    private void validateUnderMinLength(String name) {
        if (name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("이름은 %d글자 미만일 수 없습니다.%nName : %s", MIN_NAME_LENGTH, name)
            );
        }
    }

    private void validateOverMaxLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("이름은 %d글자를 초과할 수 없습니다.%nName : %s", MAX_NAME_LENGTH, name)
            );
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
