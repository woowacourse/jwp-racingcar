package racingcar.exception;

public class InvalidCarNameFormatException extends CustomException {
    private static final int ERROR_NUMBER = 103;

    public InvalidCarNameFormatException(String message) {
        super(ERROR_NUMBER, message);
    }
}
