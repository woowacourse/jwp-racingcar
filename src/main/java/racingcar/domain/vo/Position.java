package racingcar.domain.vo;

import java.util.Objects;

public class Position implements Comparable<Position> {

	private final int position;

	public Position(final int position) {
		this.position = position;
	}

	public Position move() {
		return new Position(position + 1);
	}

	public boolean isSamePosition(Position other) {
		return this.position == other.position;
	}

	@Override
	public int compareTo(Position other) {
		return this.position - other.position;
	}

	public int getValue() {
		return position;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Position position1 = (Position)o;
		return position == position1.position;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position);
	}
}
