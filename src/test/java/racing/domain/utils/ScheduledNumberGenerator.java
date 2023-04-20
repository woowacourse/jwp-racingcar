package racing.domain.utils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class ScheduledNumberGenerator implements RacingCarNumberGenerator {

    private final Deque<Integer> scheduledNumbers;

    public ScheduledNumberGenerator(Integer... scheduledNumbers) {
        List<Integer> numbers = Arrays.asList(scheduledNumbers);
        this.scheduledNumbers = new ArrayDeque<>(numbers);
    }

    @Override
    public int peekNumber() {
        if (scheduledNumbers.isEmpty()) {
            throw new IllegalStateException();
        }

        return scheduledNumbers.pollFirst();
    }
}
