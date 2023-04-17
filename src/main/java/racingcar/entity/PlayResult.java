package racingcar.entity;

import java.time.LocalDateTime;

public class PlayResult {

    private long id;
    private String winners;
    private int trialCount;
    private LocalDateTime createAt;

    public PlayResult(final long id, final String winners, final int trialCount, final LocalDateTime createAt) {
        this.id = id;
        this.winners = winners;
        this.trialCount = trialCount;
        this.createAt = createAt;
    }

    public long getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
