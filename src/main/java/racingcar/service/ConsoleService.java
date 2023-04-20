package racingcar.service;

import racingcar.domain.Cars;
import racingcar.domain.vo.Trial;

public class ConsoleService implements RacingService {

    @Override
    public Cars run(Cars cars, Trial trial) {
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
        }
        return cars;
    }

}
