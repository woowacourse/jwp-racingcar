package racingcar.global.exception.response;

import org.springframework.validation.BindingResult;
import racingcar.global.exception.ExceptionStatus;

import java.util.List;

public class ExceptionResponse {

    private final int statusCode;
    private final String message;
    private final String status;
    private final List<ExceptionTargetInfoResponse> errors;

    private ExceptionResponse(final int statusCode, final String message,
                              final String httpStatus, final List<ExceptionTargetInfoResponse> errors) {
        this.statusCode = statusCode;
        this.message = message;
        this.status = httpStatus;
        this.errors = errors;
    }

    public static ExceptionResponse of(final ExceptionStatus exceptionStatus, BindingResult bindingResult) {
        return new ExceptionResponse(exceptionStatus.getStatus(),
                                     exceptionStatus.getMessage(),
                                     exceptionStatus.getHttpStatus().name(),
                                     ExceptionTargetInfoResponse.from(bindingResult));
    }

    public static ExceptionResponse of(final ExceptionStatus exceptionStatus) {
        return new ExceptionResponse(exceptionStatus.getStatus(),
                                     exceptionStatus.getMessage(),
                                     exceptionStatus.getHttpStatus().name(),
                                     null);
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<ExceptionTargetInfoResponse> getErrors() {
        return errors;
    }
}
