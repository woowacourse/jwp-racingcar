package racingcar.exception;

public class CannotFindMaxPositionException extends CustomException {

    private static final ExceptionInformation exceptionInformation = ExceptionInformation.NOT_FOUND_MAX_POSITION;

    public CannotFindMaxPositionException() {
        super(exceptionInformation);
    }

    public CannotFindMaxPositionException(String cause) {
        super(exceptionInformation, cause);
    }
}
