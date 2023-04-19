package racingcar.domain;

import java.util.Objects;

public class TryCount {
    private static final int INIT_MIN_SIZE = 1;
    private static final int MIN_SIZE = 0;

    private int count;

    public TryCount(int count) {
        validateInitSize(count);
        this.count = count;
    }

    private void validateInitSize(final int tryCount) {
        if (tryCount < INIT_MIN_SIZE) {
            throw new IllegalArgumentException(INIT_MIN_SIZE + "보다 큰 시도 횟수만 만들 수 있습니다.");
        }
    }

    public void decrease() {
        if (isEnd()) {
            throw new IllegalArgumentException("시도 횟수를 감소시킬 수 없습니다.");
        }
        this.count--;
    }

    public boolean canTry() {
        return count > MIN_SIZE;
    }

    private boolean isEnd() {
        return count <= MIN_SIZE;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TryCount tryCount = (TryCount) o;
        return count == tryCount.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
