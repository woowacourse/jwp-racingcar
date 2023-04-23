package racingcar.utils;

import java.util.Queue;

public final class RandomPowerTestMaker implements RandomPowerGenerator{

	final Queue<Integer> store;

	public RandomPowerTestMaker (final Queue<Integer> store) {
		this.store = store;
	}

	@Override
	public int generateRandomPower () {
		return store.poll();
	}
}
