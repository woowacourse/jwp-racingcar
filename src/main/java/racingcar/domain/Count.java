package racingcar.domain;

public class Count {

    private static final int ZERO = 0;

    private final int count;

    private Count(final int count) {
        this.count = count;
    }

    public static Count of(final int count) {
        validateCountIsPositive(count);
        return new Count(count);
    }

    private static void validateCountIsPositive(final int count) {
        if (count <= ZERO) {
            throw new IllegalArgumentException("시도횟수는 양수이어야 합니다.");
        }
    }

    public int getCount() {
        return count;
    }
}
