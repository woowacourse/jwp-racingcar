package racingcar.domain;

import java.util.stream.Collectors;

public class Race {

    private static final int MIN_COUNT = 0;
    private static final int MAX_COUNT = 1000000000;
    private final int totalCount;
    private int currentCount = 0;

    public Race(final String totalCount) {
        if (!isValidCount(totalCount) || !isNum(totalCount)) {
            throw new IllegalArgumentException("부적잘한 시도 횟수입니다.");
        }
        this.totalCount = Integer.parseInt(totalCount);
    }

    private boolean isValidCount(final String input) {
        int count = Integer.parseInt(input);
        return MIN_COUNT < count && count < MAX_COUNT;
    }

    private boolean isNum(final String input) {
        if (input == null || input.equals("")) {
            return false;
        }
        return input.chars().mapToObj(c -> (char) c).collect(Collectors.toList()).stream().allMatch(Character::isDigit);
    }

    public void addCount() {
        currentCount += 1;
    }

    public boolean isFinished() {
        return totalCount == currentCount;
    }
}
