package racingcar.dto;

public class ErrorResult {
    private final String code;
    private final String message;

    public ErrorResult(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
