package racingcar.domain;

import java.util.Objects;

public class CarPosition implements Comparable<CarPosition> {

    private static final int INIT_POSITION = 1;

    private final int position;

    private CarPosition(final int position) {
        this.position = position;
    }

    public static CarPosition init() {
        return new CarPosition(INIT_POSITION);
    }

    public static CarPosition create(int position) {
        return new CarPosition(position);
    }

    public CarPosition addPosition() {
        return new CarPosition(position + 1);
    }

    @Override
    public int compareTo(final CarPosition diffPosition) {
        return position - diffPosition.position;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CarPosition that = (CarPosition) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    public int getPosition() {
        return position;
    }
}
