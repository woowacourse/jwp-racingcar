package racingcar.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static racingcar.exception.ExceptionMessage.*;

public enum ExceptionInformation {
    CANNOT_CREATE_RANDOM(ExceptionMessage.CANNOT_CREATE_RANDOM, INTERNAL_SERVER_ERROR),
    CANNOT_FIND_MAX_POSITION(ExceptionMessage.CANNOT_FIND_MAX_POSITION, INTERNAL_SERVER_ERROR),
    COMMA_NOT_FOUND(ENTER_NAME_WITH_COMMA, BAD_REQUEST),
    DATABASE_ACCESS(CANNOT_ACCESS_DATABASE, INTERNAL_SERVER_ERROR),
    NAME_IS_OUT_OF_BOUND(ENTER_NAME_CORRECT_LENGTH, BAD_REQUEST),
    NOT_POSITIVE_INTEGER(ENTER_POSITIVE_INTEGER, BAD_REQUEST),
    CANNOT_FIND_APPLICATION_TYPE(INVALID_APPLICATION_TYPE, INTERNAL_SERVER_ERROR),
    EMPTY(INVALID_EXCEPTION_MESSAGE, INTERNAL_SERVER_ERROR);

    private final ExceptionMessage exceptionMessage;
    private final HttpStatus httpStatus;

    ExceptionInformation(ExceptionMessage exceptionMessage, HttpStatus httpStatus) {
        this.exceptionMessage = exceptionMessage;
        this.httpStatus = httpStatus;
    }

    public static ExceptionInformation findByMessage(final String message) {
        return Arrays.stream(values())
                .filter(exceptionInformation -> exceptionInformation.getExceptionMessage().equals(message))
                .findFirst()
                .orElse(EMPTY);
    }

    public String getExceptionMessage() {
        return exceptionMessage.getExceptionMessage();
    }

    public int getHttpStatus() {
        return httpStatus.value();
    }
}
