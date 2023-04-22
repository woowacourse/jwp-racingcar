package racingcar;

import java.util.List;

import racingcar.utils.NumberGenerator;

public class MockNumberGenerator implements NumberGenerator {

    private final List<Integer> numbers;

    public MockNumberGenerator(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public int generateNumber() {
        return numbers.remove(0);
    }
}
