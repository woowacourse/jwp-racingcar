package racingcar.domain;

import racingcar.utils.RacingNumberGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class TestNumberGenerator implements RacingNumberGenerator {

    private final Queue<Integer> testNumbers;

    public TestNumberGenerator(List<Integer> testNumbers) {
        this.testNumbers = new LinkedList<>(testNumbers);
    }

    @Override
    public int generate() {
        return testNumbers.remove();
    }

}
