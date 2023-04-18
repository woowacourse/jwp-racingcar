package racingcar.exception;

public enum ExceptionStatus {

    DUPLICATE_CAR_NAMES(100, "중복된 차 이름이 존재합니다."),
    EXCEED_CAR_NAME_LENGTH(101, "자동차 이름은 다섯 글자 이하여야 합니다."),
    HAS_BLANK_CAR_NAME(102, "비어있는 자동차 이름이 존재합니다."),
    INVALID_CAR_NAME_FORMAT(103, "자동차 이름은 문자와 숫자만 가능합니다."),
    INVALID_RANGE_TRIAL_TIMES(104, "시도 횟수는 1 이상 100 이하여야 합니다."),
    INVALID_TRIAL_TIMES_FORMAT(105, "시도 횟수는 숫자만 입력 가능합니다.");

    private final int value;
    private final String message;

    ExceptionStatus(final int value, final String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
