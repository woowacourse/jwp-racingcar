package racingcar.service;

import java.util.List;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResponse;

public interface RacingCarService {

    RacingGameResponse play(RacingGameRequest racingGameRequest);

    List<RacingGameResponse> findGameResults();
}
