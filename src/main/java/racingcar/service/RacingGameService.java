package racingcar.service;

import racingcar.controller.response.RacingGameResponse;

import java.util.List;

public interface RacingGameService {
    RacingGameResponse race(final List<String> nameValues, final int trial);

    List<RacingGameResponse> findAllRacingGameHistories();
}
