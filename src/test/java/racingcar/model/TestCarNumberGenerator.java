package racingcar.model;

import java.util.List;

public class TestCarNumberGenerator implements CarNumberGenerator {
    private final List<Integer> isCreated;

    public TestCarNumberGenerator(final List<Integer> isCreated) {
        this.isCreated = isCreated;
    }

    @Override
    public int generate() {
        return isCreated.remove(0);
    }
}
