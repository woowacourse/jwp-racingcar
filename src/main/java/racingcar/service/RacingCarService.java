package racingcar.service;

import racingcar.dto.request.RacingStartRequest;
import racingcar.dto.response.RacingResultResponse;

public interface RacingCarService {

    RacingResultResponse play(RacingStartRequest racingStartRequest);
}
