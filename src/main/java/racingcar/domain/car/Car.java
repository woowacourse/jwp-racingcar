package racingcar.domain.car;

import racingcar.domain.strategy.MovingStrategy;

import java.util.Comparator;

public class Car {

	private static final int INITIAL_POSITION = 0;

	private final CarName name;
	private int position;

	private Car(CarName name) {
		this(name, INITIAL_POSITION);
	}

	private Car(CarName name, int position) {
		validatePositionNotNegative(position);
		this.name = name;
		this.position = position;
	}

	private void validatePositionNotNegative(final int position) {
		if (position < INITIAL_POSITION) {
			throw new IllegalArgumentException("위치는 음수가 될 수 없습니다");
		}
	}

	public static Car createCar(final String name) {
		return new Car(new CarName(name));
	}

	public static Car createCar(final String name, final int position) {
		return new Car(new CarName(name), position);
	}

	public void move(MovingStrategy movingStrategy) {
		if (movingStrategy.isMovable()) {
			position++;
		}
	}

	public boolean isSamePosition(final Car otherCar) {
		return this.position == otherCar.position;
	}

	public int getPosition() {
		return position;
	}

	public String getName() {
		return name.getName();
	}

	static class PositionComparator implements Comparator<Car> {

		@Override
		public int compare(final Car o1, final Car o2) {
			return o1.position - o2.position;
		}
	}
}
