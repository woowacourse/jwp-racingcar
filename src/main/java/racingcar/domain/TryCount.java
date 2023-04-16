package racingcar.domain;

import java.util.Objects;

public class TryCount {

    private int count;

    TryCount(int count) {
        validateCount(count);
        this.count = count;
    }

    public void decreaseCount() {
        validateCount(this.count);
        this.count--;
    }

    private void validateCount(final int tryCount) {
        if (tryCount <= 0) {
            throw new IllegalArgumentException("실행 횟수 보다 많이 실행할 수 없습니다.");
        }
    }

    public boolean isEnd() {
        return count == 0;
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
