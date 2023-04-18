package racingcar.service;

import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.Cars;

import java.util.List;

public interface RacingGameService {
    RacingGameResponse race(final Cars cars, final int trial);

    List<RacingGameResponse> findAllRacingGameHistories();
}
