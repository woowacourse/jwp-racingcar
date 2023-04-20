package racingcar.repository;

import java.util.List;
import racingcar.domain.RacingGame;

public interface RacingGameRepository {

    RacingGame insert(final RacingGame racingGame);

    List<RacingGame> findAll();
}
