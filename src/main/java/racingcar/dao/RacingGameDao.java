package racingcar.dao;

import racingcar.dao.entity.RacingGameEntity;

import java.util.List;

public interface RacingGameDao {

    int save(RacingGameEntity racingGameEntity);

    List<RacingGameEntity> findEndedRacingGameEntities();
}
