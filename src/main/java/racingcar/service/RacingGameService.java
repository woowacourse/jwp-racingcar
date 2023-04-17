package racingcar.service;

import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.Cars;

public interface RacingGameService {
    RacingGameResponse race(final Cars cars, final int count);
}
