package racingcar.service;

import java.time.LocalDateTime;
import java.util.Objects;

public class RacingResult {
    private int id;
    private final String winners;
    private final int trialCount;
    private LocalDateTime playedTime;

    public RacingResult(final String winners, final int trialCount) {
        this.winners = winners;
        this.trialCount = trialCount;
        this.playedTime = LocalDateTime.now();
    }

    public RacingResult(final int id, final String winners, final int trialCount) {
        this.id = id;
        this.winners = winners;
        this.trialCount = trialCount;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getPlayedTime() {
        return playedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RacingResult that = (RacingResult)o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RacingResult{" +
            "id=" + id +
            ", winners='" + winners + '\'' +
            ", trialCount=" + trialCount +
            ", playedTime=" + playedTime +
            '}';
    }
}
