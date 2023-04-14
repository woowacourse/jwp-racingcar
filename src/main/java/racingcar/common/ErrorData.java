package racingcar.common;

public class ErrorData<T> {
    private T data;

    public ErrorData(final T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
