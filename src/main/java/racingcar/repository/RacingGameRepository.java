package racingcar.repository;

import racingcar.dao.entity.InsertGameEntity;
import racingcar.domain.RacingGameResult;

public interface RacingGameRepository {

    InsertGameEntity save(RacingGameResult racingGameResult);

}
