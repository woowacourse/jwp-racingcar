package racingcar.repository;

import java.util.List;
import racingcar.domain.entity.RacingGameEntity;

public interface RacingCarRepository {

    void save(final RacingGameEntity racingGameResultDto);

    List<RacingGameEntity> findAll();
}
