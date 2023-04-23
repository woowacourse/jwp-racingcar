package racingcar.repository;

import java.util.List;
import racingcar.domain.entity.RacingGameEntity;

public interface RacingCarRepository {

    void save(RacingGameEntity racingGameEntity);

    List<RacingGameEntity> findAll();
}
