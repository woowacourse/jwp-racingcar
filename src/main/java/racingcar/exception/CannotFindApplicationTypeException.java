package racingcar.exception;

public class CannotFindApplicationTypeException extends CustomException {

    private static final ExceptionInformation exceptionInformation = ExceptionInformation.NOT_FOUND_APPLICATION_TYPE;

    public CannotFindApplicationTypeException() {
        super(exceptionInformation);
    }

    public CannotFindApplicationTypeException(String cause) {
        super(exceptionInformation, cause);
    }
}
