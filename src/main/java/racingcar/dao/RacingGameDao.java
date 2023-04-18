package racingcar.dao;

import racingcar.dto.RacingGameResponse;

import java.util.List;

public interface RacingGameDao {

    Long save(String winners, int trialCount);

    List<RacingGameResponse> loadHistories(CarDao carDao);
}
