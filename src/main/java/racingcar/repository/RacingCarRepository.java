package racingcar.repository;

import java.util.List;
import racingcar.domain.entity.RacingGameResultEntity;

public interface RacingCarRepository {

    void save(RacingGameResultEntity racingGameResultEntity);

    List<RacingGameResultEntity> findAll();
}
