package racingcar.strategy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class RacingTestNumberGenerator implements RacingNumberGenerator {

    private final Deque<Integer> deque;

    public RacingTestNumberGenerator(List<Integer> numbers) {
        deque = new ArrayDeque<>(numbers);
    }

    @Override
    public int generate() {
        return deque.removeFirst();
    }
}
