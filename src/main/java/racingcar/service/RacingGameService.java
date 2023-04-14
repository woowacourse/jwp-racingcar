package racingcar.service;

import racingcar.controller.dto.RacingInfoResponse;
import racingcar.domain.CarGroup;

public interface RacingGameService {

    RacingInfoResponse race(final CarGroup carGroup, final int count);
}
