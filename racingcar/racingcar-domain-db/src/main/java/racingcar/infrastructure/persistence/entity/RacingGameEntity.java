package racingcar.infrastructure.persistence.entity;

import racingcar.domain.RacingGame;

public class RacingGameEntity {

    private final Long id;
    private final int trialCount;

    public RacingGameEntity(final RacingGame racingGame) {
        this.id = null;
        this.trialCount = racingGame.getGameTimeValue();
    }

    public RacingGameEntity(final Long id, final int trialCount) {
        this.id = id;
        this.trialCount = trialCount;
    }

    public Long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
