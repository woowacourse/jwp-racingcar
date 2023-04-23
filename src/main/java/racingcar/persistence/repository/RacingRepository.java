package racingcar.persistence.repository;

import java.util.List;
import racingcar.domain.RacingGame;

public interface RacingRepository {
    void saveGameResult(final RacingGame racingGame, final int trialCount);

    List<RacingGame> findAllRacingGames();
}
