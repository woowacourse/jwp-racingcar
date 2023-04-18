package racingcar.repositiory;

import racingcar.domain.RacingGame;
import racingcar.entity.Player;

import java.util.List;

public interface RacingGameRepository {
    void save(final RacingGame racingGame);

    List<Player> findAll();
}
