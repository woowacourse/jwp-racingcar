package racingcar.utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import racingcar.domain.NumberGenerator;

public class TestNumberGenerator implements NumberGenerator {

    private final Deque<Integer> numbers;

    public TestNumberGenerator(final List<Integer> numbers) {
        this.numbers = new ArrayDeque<>(numbers);
    }

    @Override
    public int generate() {
        return numbers.removeFirst();
    }
}
