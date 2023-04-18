package racing.web.exception;

import org.springframework.http.HttpStatus;

public class RacingGameWebException extends RuntimeException {
    private final RacingGameWebExceptionType racingGameWebExceptionType;

    public RacingGameWebException(RacingGameWebExceptionType racingGameWebExceptionType) {
        this.racingGameWebExceptionType = racingGameWebExceptionType;
    }

    public HttpStatus getHttpStatus() {
        return this.racingGameWebExceptionType.getHttpStatus();
    }

    public String getExceptionMessage() {
        return this.racingGameWebExceptionType.getExceptionMessage();
    }
}
