package racingcar.service;

import racingcar.domain.RacingGame;
import racingcar.persistence.repository.RacingRepository;

public class DummyRacingRepository implements RacingRepository {
    
    @Override
    public void saveGameResult(RacingGame racingGame, int trialCount) {
    }
}
