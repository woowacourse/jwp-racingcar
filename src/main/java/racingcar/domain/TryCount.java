package racingcar.domain;

public class TryCount {
    final int count;

    public TryCount(final int count) {
        validateSize(count);
        this.count = count;
    }

    private void validateSize(final int count) {
        if (count < 1) {
            throw new IllegalArgumentException("이동 횟수는 1회 이상이여야 합니다.");
        }
    }

    public int getCount() {
        return count;
    }
}
