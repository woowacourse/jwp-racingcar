package racingcar.domain;

public class TryCount {

    private int count;

    TryCount(int count) {
        validateCount(count);
        this.count = count;
    }

    private void validateCount(final int tryCount) {
        if (tryCount <= 0) {
            throw new IllegalArgumentException("실행 횟수는 1 이상이여야 합니다." + System.lineSeparator() + "tryCount : " + tryCount);
        }
    }

    public boolean isEnd() {
        return count == 0;
    }

    public int getCount() {
        return count;
    }

    public void decreaseCount() {
        this.count--;
    }
}
