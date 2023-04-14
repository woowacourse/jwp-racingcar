package racingcar.utils;

import racingcar.domain.NumberGenerator;

import java.util.List;

public class TestNumberGenerator implements NumberGenerator {

    private final List<Integer> numbers;

    public TestNumberGenerator(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public int generate() {
        return numbers.remove(0);
    }
}
