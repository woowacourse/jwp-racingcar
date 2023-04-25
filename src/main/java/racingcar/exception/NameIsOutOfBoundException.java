package racingcar.exception;

public class NameIsOutOfBoundException extends CustomException {

    private static final ExceptionInformation exceptionInformation = ExceptionInformation.OUT_OF_BOUND_NAME;

    public NameIsOutOfBoundException() {
        super(exceptionInformation);
    }

    public NameIsOutOfBoundException(String cause) {
        super(exceptionInformation, cause);
    }
}
