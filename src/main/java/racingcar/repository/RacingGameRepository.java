package racingcar.repository;

import java.util.List;
import racingcar.domain.game.RacingGame;

public interface RacingGameRepository {
    RacingGame save(RacingGame racingGame, int trialCount);
    List<RacingGame> findAll();
}
