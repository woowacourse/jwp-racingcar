package racingcar.dao.entity;

import java.util.Objects;

public class CarId {

    private final Integer value;

    public CarId(final Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarId carId = (CarId) o;
        return Objects.equals(value, carId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Integer getValue() {
        return value;
    }
}
