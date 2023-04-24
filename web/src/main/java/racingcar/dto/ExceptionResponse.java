package racingcar.dto;

public class ExceptionResponse<T> {

    private final T body;

    public ExceptionResponse(final T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }
}
