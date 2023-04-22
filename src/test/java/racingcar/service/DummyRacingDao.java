package racingcar.service;

import java.util.List;
import racingcar.dao.RacingDao;
import racingcar.dto.CarDto;
import racingcar.dto.RacingResultDto;

public class DummyRacingDao implements RacingDao {
    @Override
    public int saveGameResult(final RacingResultDto racingResultDto, final int trialCount) {
        return 0;
    }

    @Override
    public void savePlayerResults(final List<CarDto> racingCars, final int gameResultId) {
    }
}
