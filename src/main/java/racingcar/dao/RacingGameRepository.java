package racingcar.dao;

import racingcar.entity.RacingGameEntity;

import java.util.List;

public interface RacingGameRepository {

    Long save(final RacingGameEntity racingGameEntity);

    List<RacingGameEntity> findAll();
}
