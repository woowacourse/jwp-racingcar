package racingcar.domain;

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
            throw new IllegalArgumentException("1에서 5사이의 이름 길이만 입력 가능합니다.");
        }
    }

    private void validateContainsBlank(String carNames) {
        if (carNames.contains(" ")) {
            throw new IllegalArgumentException("자동차 이름은 공백을 포함할 수 없습니다.");
        }
    }

    String name() {
        return name;
    }
}
