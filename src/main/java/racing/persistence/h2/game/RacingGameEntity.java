package racing.persistence.h2.game;

import java.time.LocalDateTime;

public class RacingGameEntity {

    private final Long gameId;
    private final LocalDateTime createTime;
    private final int trialCount;

    public RacingGameEntity(Long gameId, LocalDateTime createTime, int trialCount) {
        this.gameId = gameId;
        this.createTime = createTime;
        this.trialCount = trialCount;
    }

    public Long getGameId() {
        return gameId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
