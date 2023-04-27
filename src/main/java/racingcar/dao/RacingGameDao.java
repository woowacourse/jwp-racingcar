package racingcar.dao;

import java.util.List;
import racingcar.domain.entity.RacingGameResultEntity;

public interface RacingGameDao {

    int save(int count);

    List<RacingGameResultEntity> findAll();
}
