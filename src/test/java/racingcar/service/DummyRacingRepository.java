package racingcar.service;

import java.util.List;
import racingcar.domain.RacingGame;
import racingcar.persistence.repository.RacingRepository;

public class DummyRacingRepository implements RacingRepository {

    @Override
    public void saveGameResult(RacingGame racingGame, int trialCount) {
    }

    @Override
    public List<RacingGame> findAllRacingGames() {
        return null;
    }
}
