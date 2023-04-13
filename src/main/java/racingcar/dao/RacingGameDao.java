package racingcar.dao;

import racingcar.dto.CarDto;

import java.util.List;

public interface RacingGameDao {

    Number saveGameResult(final String winners, final int trialCount);

    void savePlayerResults(final List<CarDto> racingCars, final Number gameResultKey);
}
