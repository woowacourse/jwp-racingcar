package racingcar.repository;

import java.util.List;
import racingcar.dao.entity.InsertGameEntity;
import racingcar.domain.RacingGameResult;

public interface RacingGameRepository {

    InsertGameEntity save(RacingGameResult racingGameResult);

    List<RacingGameResult> findAll();
}
