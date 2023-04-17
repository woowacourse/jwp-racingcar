package racingcar.repository;

import racingcar.domain.RacingGame;
import racingcar.dao.entity.InsertGameEntity;

public interface RacingGameRepository {

    InsertGameEntity save(RacingGame racingGame);

}
