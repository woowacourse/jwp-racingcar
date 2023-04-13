package racingcar.dao;

import racingcar.dto.CarData;

import java.util.List;

public interface RacingGameDao {

    Number saveGameResult(final String winners, final int trialCount);

    void savePlayerResults(final List<CarData> racingCarData, final Number gameResultKey);
}
