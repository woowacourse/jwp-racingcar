package racingcar.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

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
