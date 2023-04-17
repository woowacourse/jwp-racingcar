package racingcar.domain;

import racingcar.error.ErrorMessage;

public class TryCount {
    private static final int MIN_COUNT = 1;

    private final int count;

    private TryCount(int count) {
        this.count = count;
    }

    public static TryCount from(int count) {
        validate(count);

        return new TryCount(count);
    }

    private static void validate(int count) {
        if (count < MIN_COUNT) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.INVALID_COUNT.getValue(), MIN_COUNT)
            );
        }
    }

    public int getCount() {
        return count;
    }
}
