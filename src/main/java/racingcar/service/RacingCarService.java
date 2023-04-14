package racingcar.service;

import java.util.List;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResultResponse;

public interface RacingCarService {

    RacingGameResultResponse play(RacingGameRequest racingGameRequest);

    List<RacingGameResultResponse> findGameResults();
}
