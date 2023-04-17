package racingcar.dto;

public class ExceptionResponse<T> {

    private final T message;

    public ExceptionResponse(final T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }
}
