package racingcar.exception;

public class CustomException extends IllegalArgumentException {

    public CustomException(final String message) {
        super(message);
    }

    public String getMessage() {return super.getMessage();}
}
