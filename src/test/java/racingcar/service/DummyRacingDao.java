package racingcar.service;

import racingcar.dao.RacingDao;
import racingcar.domain.RacingGame;

public class DummyRacingDao implements RacingDao {
    @Override
    public int saveGameResult(final RacingGame racingGame, final int trialCount) {
        return 0;
    }

    @Override
    public void savePlayerResults(final RacingGame racingGame, final int gameResultId) {
    }
}
