package racingcar.model;

import racingcar.exception.BadRequestException;

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
}
