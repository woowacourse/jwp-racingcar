package racingcar.service;

import racingcar.domain.Cars;
import racingcar.domain.vo.Trial;

public interface RacingService {

    Cars run(Cars cars, Trial trial);

}
