package racingcar.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class GameResult {
    private final Long id;
    private final int trialCount;
    private final String winners;
    private final Timestamp createdAt;

    public GameResult(Long id, int trialCount, String winners, Timestamp createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public GameResult(int trialCount, String winners, Timestamp createdAt) {
        this(null, trialCount, winners, createdAt);
    }

    public Long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trialCount, winners, createdAt);
    }
}
