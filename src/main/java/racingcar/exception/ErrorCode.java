package racingcar.exception;

public enum ErrorCode {
    INVALID_NAME_LENGTH(400, "이름은 %d글자 이하여야 합니다."),
    INVALID_COUNT(400, "시도 횟수는 %d회 이상이여야 합니다."),
    DUPLICATED_NAME(400, "증복된 이름이 존재합니다." );

    private static final String ERROR_HEAD = "[ERROR]: %s";

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = String.format(ERROR_HEAD, message);
    }

    public String getMessage() {
        return message;
    }
}
