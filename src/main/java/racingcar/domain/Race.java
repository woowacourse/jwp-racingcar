package racingcar.domain;

public class Race {

    private static final int RACE_MIN_TRY_COUNT = 0;
    private static final String TYPE_MESSAGE = "%s은(는) 숫자만 입력할 수 있습니다.";
    private static final String RANGE_MESSAGE = "%d 이상의 값을 입력해 주세요.";

    private final int count;

    private Race(final String raceCount) {
        int count = validateType(raceCount);
        this.count = validateRange(count);
    }

    public static Race create(final String raceCount) {
        return new Race(raceCount);
    }

    public boolean isRunning(int raceCount) {
        return raceCount != count;
    }

    private int validateType(final String raceCount) {
        int count;
        try {
            count = Integer.parseInt(raceCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(TYPE_MESSAGE, "시도 횟수"));
        }
        return count;
    }

    private int validateRange(final int raceCount) {
        if (raceCount <= RACE_MIN_TRY_COUNT) {
            throw new IllegalArgumentException(String.format(RANGE_MESSAGE, RACE_MIN_TRY_COUNT));
        }
        return raceCount;
    }

    public int getCount() {
        return count;
    }
}
