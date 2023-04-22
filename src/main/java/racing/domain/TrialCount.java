package racing.domain;

public class TrialCount {
    private static final String NOT_INTEGER_FORMAT = "시도 횟수는 정수만 입력 가능 합니다.";

    private final int trialCount;

    public TrialCount(int trialCount) {
        this.trialCount = trialCount;
    }

    public static TrialCount valueOf(String value) {
        try {
            int toInt = Integer.parseInt(value);
            return new TrialCount(toInt);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NOT_INTEGER_FORMAT);
        }
    }

    public int getValue() {
        return this.trialCount;
    }
}
