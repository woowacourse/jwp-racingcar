package racingcar.dao;

import java.util.List;

import racingcar.service.RacingResult;

public interface GameDao {
    RacingResult insertRacingResult(RacingResult racingResult);

    List<RacingResult> selectAllResults();
}
