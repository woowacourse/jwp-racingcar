package racingcar.dao.entity;

import java.util.Objects;

public class GameId {

    private final Integer value;

    public GameId(final Integer value) {
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
        final GameId gameId = (GameId) o;
        return Objects.equals(value, gameId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Integer getValue() {
        return value;
    }
}
