package racingcar.repository;

import java.util.List;
import racingcar.dto.CarEntity;
import racingcar.dto.RacingGameEntity;

public interface RacingCarRepository {

    void save(RacingGameEntity racingGameResultDto);

    List<CarEntity> findAll();
}
