package racingcar.repository;

import racingcar.domain.RacingGame;
import racingcar.dao.entity.GameEntity;

public interface RacingGameRepository {

    GameEntity save(RacingGame racingGame);

}
