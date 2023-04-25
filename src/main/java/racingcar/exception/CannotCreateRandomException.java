package racingcar.exception;

public class CannotCreateRandomException extends CustomException {

    private static final ExceptionInformation exceptionInformation = ExceptionInformation.CANNOT_CREATE_RANDOM_NUMBER;

    public CannotCreateRandomException() {
        super(exceptionInformation);
    }

    public CannotCreateRandomException(String cause) {
        super(exceptionInformation, cause);
    }
}

