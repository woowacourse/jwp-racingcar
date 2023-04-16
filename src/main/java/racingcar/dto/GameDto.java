package racingcar.dto;

public class GameDto {

    private static final String NUMBER_OF_TIME_ERROR = "시도할 횟수는 1~100 사이값만 가능합니다.";
    private static final int NUMBER_OF_TIME_LOWER_BOUND = 0;
    private static final int NUMBER_OF_TIME_UPPER_BOUND = 101;

    private final String names;
    private final int count;

    public GameDto(final String names, final int count) {
        validateCount(count);
        this.names = names;
        this.count = count;
    }

    public void validateCount(final int number) {
        if (number <= NUMBER_OF_TIME_LOWER_BOUND || NUMBER_OF_TIME_UPPER_BOUND <= number) {
            throw new IllegalArgumentException(NUMBER_OF_TIME_ERROR);
        }
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
