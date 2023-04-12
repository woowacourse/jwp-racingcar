package racingcar;

import java.util.List;
import racingcar.domain.NumberGenerator;

public class FixNumberGenerator implements NumberGenerator {

    private final List<Integer> numbers;

    public FixNumberGenerator(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public int generate() {
        return numbers.remove(0);
    }
}
