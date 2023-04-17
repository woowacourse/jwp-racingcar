package racing.controller.common.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
    private final BusinessExceptionType businessExceptionType;

    public BusinessException(BusinessExceptionType businessExceptionType) {
        this.businessExceptionType = businessExceptionType;
    }

    public HttpStatus getHttpStatus() {
        return this.businessExceptionType.getHttpStatus();
    }

    public String getExceptionMessage() {
        return this.businessExceptionType.getExceptionMessage();
    }
}
