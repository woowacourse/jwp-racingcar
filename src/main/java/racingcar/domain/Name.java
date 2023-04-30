package racingcar.domain;

public class Name {

    private static final int MAX_LENGTH = 5;
    private static final String BLANK_EXCEPTION = "[ERROR] 자동차 이름은 공백이거나 빌 수 없습니다.";
    private static final String LENGTH_EXCEPTION = "[ERROR] 자동차 이름 길이는 5를 넘을 수 없습니다.";

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNullOrBlank(name);
        validateLength(name);
    }

    private void validateNullOrBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(BLANK_EXCEPTION);
        }
    }

    private void validateLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(LENGTH_EXCEPTION);
        }
    }

    public String getName() {
        return name;
    }
}
