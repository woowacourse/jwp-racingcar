package racingcar.service;

import racingcar.dao.RacingGameDao;
import racingcar.dto.CarDto;

import java.util.List;

public class DummyRacingGameDao implements RacingGameDao {
    @Override
    public Number saveGameResult(final String winners, final int trialCount) {
        return null;
    }

    @Override
    public void savePlayerResults(final List<CarDto> racingCars, final Number gameResultKey) {
    }
}
