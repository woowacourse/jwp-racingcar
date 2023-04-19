package racingcar.service;

import java.util.List;
import racingcar.dao.RacingGameDao;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;

public class DummyRacingGameDao implements RacingGameDao {
    @Override
    public int saveGameResult(final GameResultDto gameResultDto, final int trialCount) {
        return 0;
    }

    @Override
    public void savePlayerResults(final List<CarDto> racingCars, final int gameResultId) {
    }
}
