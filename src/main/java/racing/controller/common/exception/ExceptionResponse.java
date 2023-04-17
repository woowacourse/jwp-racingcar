package racing.controller.common.exception;

public class ExceptionResponse {
    private final String httpStatusMessage;
    private final String errorMessage;

    public ExceptionResponse(String httpStatusMessage, String errorMessage) {
        this.httpStatusMessage = httpStatusMessage;
        this.errorMessage = errorMessage;
    }

    public ExceptionResponse(BusinessException businessException) {
        this.httpStatusMessage = businessException.getHttpStatus().name();
        this.errorMessage = businessException.getExceptionMessage();
    }

    public String getHttpStatusMessage() {
        return httpStatusMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
