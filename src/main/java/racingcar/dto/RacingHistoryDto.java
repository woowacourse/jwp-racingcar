package racingcar.dto;

import java.time.LocalDateTime;

public class RacingHistoryDto {

    private final long id;
    private final int trialCount;
    private final LocalDateTime playTime;

    public RacingHistoryDto(long id, int trialCount, LocalDateTime playTime) {
        this.id = id;
        this.trialCount = trialCount;
        this.playTime = playTime;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getPlayTime() {
        return playTime;
    }

    public long getId() {
        return id;
    }

}
