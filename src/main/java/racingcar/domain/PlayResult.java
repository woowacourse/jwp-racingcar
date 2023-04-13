package racingcar.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class PlayResult {
    private final Long id;
    private final int trialCount;
    private final String winners;
    private final Timestamp createdAt;

    private PlayResult(Long id, int trialCount, String winners, Timestamp createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public static PlayResult of(int trialCount, String winners, Timestamp createdAt) {
        return new PlayResult(null, trialCount, winners, createdAt);
    }

    public static PlayResult of(Long id, int trialCount, String winners, Timestamp createdAt) {
        return new PlayResult(id, trialCount, winners, createdAt);
    }

    public long getId() {
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
        PlayResult that = (PlayResult) o;
        return !id.equals(null) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trialCount, winners, createdAt);
    }
}
