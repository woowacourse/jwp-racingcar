package racing.web.exception;

public class ExceptionResponse {
    private final String httpStatusMessage;
    private final String errorMessage;

    public ExceptionResponse(String httpStatusMessage, String errorMessage) {
        this.httpStatusMessage = httpStatusMessage;
        this.errorMessage = errorMessage;
    }

    public ExceptionResponse(RacingGameWebException racingGameWebException) {
        this.httpStatusMessage = racingGameWebException.getHttpStatus().name();
        this.errorMessage = racingGameWebException.getExceptionMessage();
    }

    public String getHttpStatusMessage() {
        return httpStatusMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
