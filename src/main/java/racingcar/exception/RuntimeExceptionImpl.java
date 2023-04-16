package racingcar.exception;

import org.springframework.http.HttpStatus;

public abstract class RuntimeExceptionImpl extends RuntimeException {

    private static final String ERROR_TAG = "[ERROR] ";
    private final HttpStatus httpStatus;

    public RuntimeExceptionImpl(String message, HttpStatus httpStatus) {
        super(makeDefaultErrorMessage(message));
        this.httpStatus = httpStatus;
    }

    private static String makeDefaultErrorMessage(final String message) {
        return ERROR_TAG + message;
    }

    public int getHttpStatusValue() {
        return httpStatus.value();
    }
}
