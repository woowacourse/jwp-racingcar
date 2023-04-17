package racingcar.repository;

import racingcar.dao.entity.GameEntity;
import racingcar.domain.RacingGame;

public interface RacingGameRepository {

    GameEntity save(RacingGame racingGame);

}
