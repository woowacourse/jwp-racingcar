package racingcar.dao;

import java.time.LocalDateTime;

public class CarRecordWithGameHistoryDto {
    private final Long id;
    private final String name;
    private final int position;
    private final Long gameId;
    private final LocalDateTime playTime;
    private final int trialCount;

    public CarRecordWithGameHistoryDto(Long id, String name, int position, Long gameId, LocalDateTime playTime,
                                       int trialCount) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
        this.playTime = playTime;
        this.trialCount = trialCount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Long getGameId() {
        return gameId;
    }

    public LocalDateTime getPlayTime() {
        return playTime;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
