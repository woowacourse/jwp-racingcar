package racingcar.dao;

import racingcar.domain.RacingGame;

public interface RacingDao {

    int saveGameResult(final RacingGame racingGame, final int trialCount);

    void savePlayerResults(final RacingGame racingGame, final int gameResultId);
}
