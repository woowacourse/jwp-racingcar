package racingcar.domain.exception;

public class RacingCarIllegalArgumentException extends RuntimeException {

    public RacingCarIllegalArgumentException() {
    }

    public RacingCarIllegalArgumentException(final String message) {
        super(message);
    }

    public RacingCarIllegalArgumentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RacingCarIllegalArgumentException(final Throwable cause) {
        super(cause);
    }

    public RacingCarIllegalArgumentException(final String message, final Throwable cause,
                                             final boolean enableSuppression,
                                             final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
