package racingcar.domain.strategy;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberMovingStrategy implements MovingStrategy {

	private static final int MOVABLE_NUMBER_LOWER_BOUND = 4;
	private static final int UPPER_BOUND = 10;

	private static final Random random = new Random();

	@Override
	public boolean isMovable() {
		return random.nextInt(UPPER_BOUND) >= MOVABLE_NUMBER_LOWER_BOUND;
	}
}
