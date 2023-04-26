package racingcar.dao;

import java.util.List;
import racingcar.dao.entity.RacingGameEntity;

public interface RacingGameDao {

    Long save(RacingGameEntity racingGameEntity);

    List<RacingGameEntity> findAllByCreatedTimeAsc();
}
