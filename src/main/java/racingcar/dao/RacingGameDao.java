package racingcar.dao;

import java.util.List;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;

public interface RacingGameDao {

    int saveGameResult(final GameResultDto gameResultDto, final int trialCount);

    void savePlayerResults(final List<CarDto> racingCars, final int gameResultId);
}
