package racingcar.web.util;

import java.util.List;

public class TestNumberGenerator implements NumberGenerator {
    private final List<Integer> numbers;

    public TestNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public int generateNumber() {
        return numbers.remove(0);
    }
}
