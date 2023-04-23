package racingcar.domain;

public class TryCount {
    private static final int EXCEPTION_FLAG = 0;

    private final int tryCount;

    public TryCount(final int count) {
        validateInputRange(count);
        this.tryCount = count;
    }

    private void validateInputRange(final int inputCount) {
        if (inputCount <= EXCEPTION_FLAG) {
            throw new IllegalArgumentException("[ERROR] 양의 정수를 입력해주세요.");
        }
    }

    public int getCount() {
        return this.tryCount;
    }
}
