package racingcar.domain.strategy;

public class NumberMovingStrategy implements MovingStrategy {

	private static final int MOVABLE_NUMBER_LOWER_BOUND = 4;
	private final NumberGenerator numberGenerator;

	public NumberMovingStrategy(NumberGenerator numberGenerator) {
		this.numberGenerator = numberGenerator;
	}

	@Override
	public boolean isMovable() {
		return numberGenerator.generateInt() >= MOVABLE_NUMBER_LOWER_BOUND;
	}
}
