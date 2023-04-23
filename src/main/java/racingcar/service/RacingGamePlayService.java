package racingcar.service;

import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

public interface RacingGamePlayService {

    RacingGameResponse play(RacingGameRequest racingGameRequest);
}
