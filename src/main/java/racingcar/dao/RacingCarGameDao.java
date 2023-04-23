package racingcar.dao;

import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;

public interface RacingCarGameDao {
    int insertGame(final RacingCars racingCars, final TryCount tryCount);
}
