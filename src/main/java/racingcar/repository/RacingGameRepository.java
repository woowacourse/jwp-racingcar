package racingcar.repository;

import java.util.List;
import racingcar.domain.game.RacingGame;

public interface RacingGameRepository {
    RacingGame create(RacingGame racingGame);
    List<RacingGame> findAll();
}
