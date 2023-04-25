package racingcar.exception;

public abstract class CustomException extends RuntimeException {

    public static final String CAUSE_TAG = "\n에러 발생 지점 >> ";
    private static final String ERROR_TAG = "[ERROR] ";
    private final ExceptionInformation exceptionInformation;

    public CustomException(ExceptionInformation exceptionInformation) {
        super(makeDefaultErrorMessage(exceptionInformation));
        this.exceptionInformation = exceptionInformation;
    }

    public CustomException(ExceptionInformation exceptionInformation, String cause) {
        super(makeErrorMessageWithCause(exceptionInformation, cause));
        this.exceptionInformation = exceptionInformation;
    }

    private static String makeDefaultErrorMessage(ExceptionInformation exceptionInformation) {
        return ERROR_TAG + exceptionInformation.getExceptionMessage();
    }

    private static String makeErrorMessageWithCause(final ExceptionInformation exceptionInformation, String cause) {
        return ERROR_TAG + exceptionInformation.getExceptionMessage() + CAUSE_TAG + cause;
    }

    public int getHttpStatus() {
        return exceptionInformation.getHttpStatus();
    }
}
