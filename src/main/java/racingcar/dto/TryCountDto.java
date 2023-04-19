package racingcar.dto;

import static racingcar.exception.ExceptionMessage.ILLEGAL_TRY_COUNT;

public class TryCountDto {
    private final int tryCount;

    private TryCountDto(int tryCount) {
        this.tryCount = tryCount;
    }

    public static TryCountDto of(String input) {
        int tryCount = stringToInt(input);
        return new TryCountDto(tryCount);
    }

    private static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ILLEGAL_TRY_COUNT.getMessage());
        }
    }

    public int getTryCount() {
        return tryCount;
    }
}
