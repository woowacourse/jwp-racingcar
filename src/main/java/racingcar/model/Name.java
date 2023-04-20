package racingcar.model;

import racingcar.exception.BadRequestException;

import java.util.Objects;

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
            throw new BadRequestException("이름은 공백일 수 없습니다.");
        }
        if (!validateLength(name)) {
            throw new BadRequestException("1 ~ 5글자 사이의 이름을 입력해주세요.");
        }
    }

    private boolean validateLength(String name) {
        int length = name.length();

        return length >= NAME_MIN_LENGTH && length <= NAME_MAX_LENGTH;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
