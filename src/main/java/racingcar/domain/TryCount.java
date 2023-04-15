package racingcar.domain;

public final class TryCount {

    private static final int MIN_COUNT = 1;

    private int count;

    public TryCount(final int count) {
        validate(count);
        this.count = count;
    }

    public TryCount decreaseCount() {
        if (this.count > 0) {
            return new TryCount(count--);
        }
        throw new IllegalStateException("시도 횟수를 초과하셨습니다.");
    }

    private void validate(int count) {
        if (count < MIN_COUNT) {
            throw new IllegalArgumentException(String.format("시도 횟수는 %d회 이상이여야 합니다.", MIN_COUNT));
        }
    }

    public int getCount() {
        return count;
    }
}
