package racingcar.service;

import racingcar.dao.RacingGameDao;
import racingcar.dto.CarData;

import java.util.List;

public class DummyRacingGameDao implements RacingGameDao {
    @Override
    public Number saveGameResult(final String winners, final int trialCount) {
        return null;
    }

    @Override
    public void savePlayerResults(final List<CarData> racingCarData, final Number gameResultKey) {
    }
}
