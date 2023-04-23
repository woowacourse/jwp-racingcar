package racingcar.exception;

public class CommaNotFoundException extends CustomException {

    private static final ExceptionInformation exceptionInformation = ExceptionInformation.NOT_FOUND_COMMA;

    public CommaNotFoundException() {
        super(exceptionInformation);
    }

    public CommaNotFoundException(String cause) {
        super(exceptionInformation, cause);
    }
}
