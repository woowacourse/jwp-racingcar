package racingcar.service;

import racingcar.controller.dto.GameInfoRequest;
import racingcar.domain.RacingCars;
import racingcar.util.NumberGenerator;

public abstract class RaceService {

    private final NumberGenerator numberGenerator;

    protected RaceService(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public RacingCars race(final GameInfoRequest gameInfoRequest) {
        final String names = gameInfoRequest.getNames();
        final RacingCars racingCars = RacingCars.makeCars(names);

        final int tryCount = gameInfoRequest.getCount();

        racingCars.moveAllCars(tryCount, numberGenerator);

        return racingCars;
    }
}
