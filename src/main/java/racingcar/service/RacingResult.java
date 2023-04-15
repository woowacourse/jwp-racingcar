package racingcar.service;

import java.time.LocalDateTime;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/11
 */
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
}
