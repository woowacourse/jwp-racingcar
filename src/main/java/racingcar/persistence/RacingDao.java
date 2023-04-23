package racingcar.persistence;

import racingcar.domain.RacingGame;
import racingcar.persistence.entity.GameResultEntity;

public interface RacingDao {

    GameResultEntity saveGameResult(final RacingGame racingGame, final int trialCount);

    void savePlayerResults(final RacingGame racingGame, final long gameResultId);
}
