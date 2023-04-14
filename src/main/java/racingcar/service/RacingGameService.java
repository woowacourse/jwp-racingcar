package racingcar.service;

import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.CarGroup;

public interface RacingGameService {

    RacingGameResponse race(final CarGroup carGroup, final int count);
}
