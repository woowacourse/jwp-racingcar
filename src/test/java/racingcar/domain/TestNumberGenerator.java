package racingcar.domain;

import java.util.List;

public class TestNumberGenerator implements NumberGenerator {

    private final List<Integer> values;
    private int index = 0;

    public TestNumberGenerator(List<Integer> values) {
        this.values = values;
    }

    @Override
    public int generate() {
        return values.get(index++);
    }
}
