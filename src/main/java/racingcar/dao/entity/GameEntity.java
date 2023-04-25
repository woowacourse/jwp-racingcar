package racingcar.dao.entity;

import java.util.Objects;

public class GameEntity {

    private final Long id;
    private final int trialCount;

    public GameEntity(final Long id, final int trialCount) {
        this.id = id;
        this.trialCount = trialCount;
    }

    public Long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GameEntity that = (GameEntity) o;
        return trialCount == that.trialCount && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trialCount);
    }
}
