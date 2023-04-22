package racingcar.dao;

import java.util.List;
import racingcar.dto.CarDto;
import racingcar.dto.RacingResultDto;

public interface RacingDao {

    int saveGameResult(final RacingResultDto racingResultDto, final int trialCount);

    void savePlayerResults(final List<CarDto> racingCars, final int gameResultId);
}
