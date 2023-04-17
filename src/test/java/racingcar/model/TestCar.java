package racingcar.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TestCar extends Car {
    private final Queue<Integer> randomNumbers;

    public TestCar(final String name, final List<Integer> randomNumbers) {
        super(name);
        this.randomNumbers = new LinkedList<>(randomNumbers);
    }

    @Override
    public boolean isMove() {
        return randomNumbers.poll().intValue() >= 4;
    }
}
