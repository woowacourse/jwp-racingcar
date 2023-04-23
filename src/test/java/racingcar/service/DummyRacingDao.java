package racingcar.service;

import java.util.Date;
import racingcar.domain.RacingGame;
import racingcar.persistence.RacingDao;
import racingcar.persistence.entity.GameResultEntity;

public class DummyRacingDao implements RacingDao {
    @Override
    public GameResultEntity saveGameResult(final RacingGame racingGame, final int trialCount) {
        return new GameResultEntity(0, 0, "dummy", new Date());
    }
    
    @Override
    public void savePlayerResults(final RacingGame racingGame, final long gameResultId) {
    }
}
