package racingcar.service;

import java.time.LocalDateTime;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/11
 */
public class PlayResult {
    private int id;
    private String winners;
    private int trialCount;
    private LocalDateTime playedTime;

    public PlayResult(final String winners, final int trialCount) {
        this.winners = winners;
        this.trialCount = trialCount;
        this.playedTime = LocalDateTime.now();
    }

    public PlayResult(final int id, final String winners, final int trialCount) {
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
