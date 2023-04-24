package racingcar.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import racingcar.strategy.RacingNumberGenerator;

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
