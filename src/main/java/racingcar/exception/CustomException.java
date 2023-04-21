package racingcar.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends IllegalArgumentException {
    private String errorMessage;

    public CustomException(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
