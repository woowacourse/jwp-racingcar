package racingcar.infrastructure.persistence.entity;

import racingcar.domain.RacingGame;

public class RacingGameEntity {

    private final int trialCount;

    public RacingGameEntity(final RacingGame racingGame) {
        this.trialCount = racingGame.getGameTimeValue();
    }

    public RacingGameEntity(final int trialCount) {
        this.trialCount = trialCount;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
