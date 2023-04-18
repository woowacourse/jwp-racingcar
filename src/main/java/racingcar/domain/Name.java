package racingcar.domain;

public class Name {
    private static final int NAME_MAX_LENGTH = 5;
    private static final int NAME_MIN_LENGTH = 0;

    private final String value;

    public Name(String value) {
        String trimName = value.trim();
        validateNameLength(trimName);
        this.value = trimName;
    }

    private void validateNameLength(String name) {
        if (name.length() > NAME_MAX_LENGTH || name.length() <= NAME_MIN_LENGTH) {
            throw new IllegalArgumentException("자동차 이름의 길이는 1부터 5 사이여야 합니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
