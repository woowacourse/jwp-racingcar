package racingcar.infrastructure.persistence.entity;

import racingcar.domain.RacingGame;

import java.time.LocalDate;

public class RacingGameEntity {

    private Long id;
    private LocalDate createdAt;
    private int trialCount;

    public RacingGameEntity(final RacingGame racingGame) {
        this.trialCount = racingGame.getGameTimeValue();
    }

    public RacingGameEntity(final Long id, final LocalDate createdAt, final int trialCount) {
        this.id = id;
        this.createdAt = createdAt;
        this.trialCount = trialCount;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCreatedAt(final LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
