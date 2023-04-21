package racingcar.dao.entity;

import java.util.Objects;

public class WinnerId {

    private final Integer value;

    public WinnerId(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final WinnerId winnerId = (WinnerId) o;
        return Objects.equals(value, winnerId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
