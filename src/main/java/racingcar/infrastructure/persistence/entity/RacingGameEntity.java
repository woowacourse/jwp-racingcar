package racingcar.infrastructure.persistence.entity;

import racingcar.domain.RacingGame;

import java.time.LocalDateTime;

public class RacingGameEntity {

    private Long id;
    private LocalDateTime createdAt;
    private int trialCount;

    public RacingGameEntity(final RacingGame racingGame) {
        this.trialCount = racingGame.getGameTimeValue();
    }

    public RacingGameEntity(final Long id, final LocalDateTime createdAt, final int trialCount) {
        this.id = id;
        this.createdAt = createdAt;
        this.trialCount = trialCount;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
