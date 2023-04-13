package racingcar.error;

public enum ErrorMessage {
    INVALID_NAME_LENGTH("이름은 %d글자 이하여야 합니다."),
    INVALID_COUNT("시도 횟수는 %d회 이상이여야 합니다.");

    private final String value;
    private static final String ERROR_HEAD = "[ERROR]: %s";

    ErrorMessage(String message) {
        this.value = String.format(ERROR_HEAD, message);
    }

    public String getValue() {
        return value;
    }
}
