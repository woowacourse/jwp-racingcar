package racingcar.service;

import racingcar.domain.RacingGame;
import racingcar.repository.entity.GameEntity;

public interface RacingGameRepository {

    GameEntity save(RacingGame racingGame);

}
