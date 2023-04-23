package racingcar.exception;

public class NotPositiveIntegerException extends CustomException {

    private static final ExceptionInformation exceptionInformation = ExceptionInformation.NOT_POSITIVE_INTEGER;


    public NotPositiveIntegerException() {
        super(exceptionInformation);
    }

    public NotPositiveIntegerException(String cause) {
        super(exceptionInformation, cause);
    }
}
