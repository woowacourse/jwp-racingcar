package racingcar.repositiory;

import racingcar.domain.RacingGame;
import racingcar.dto.GameResponse;

import java.util.List;

public interface RacingGameRepository {
    void save(final RacingGame racingGame);
    List<GameResponse> findAll();
}
