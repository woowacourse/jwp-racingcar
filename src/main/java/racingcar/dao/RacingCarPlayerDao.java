package racingcar.dao;

import java.util.List;
import racingcar.domain.RacingCars;

public interface RacingCarPlayerDao {
    void insertGameLog(final RacingCars racingCars, final int gameId);

    List<RacingCars> findAll();
}
