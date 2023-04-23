package racingcar.persistence.repository;

import racingcar.domain.RacingGame;

public interface RacingRepository {
    void saveGameResult(final RacingGame racingGame, final int trialCount);
}
