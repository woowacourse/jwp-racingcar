package racingcar.domain;

import java.util.Objects;

public class CarPosition implements Comparable<CarPosition> {

    private static final int INIT_POSITION = 1;

    private final int position;

    public CarPosition(final int position) {
        this.position = position;
    }

    public static CarPosition create() {
        return new CarPosition(INIT_POSITION);
    }

    public CarPosition addPosition() {
        return new CarPosition(position + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarPosition that = (CarPosition) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public int compareTo(final CarPosition o) {
        return position - o.position;
    }

    public int getPosition() {
        return position;
    }
}
