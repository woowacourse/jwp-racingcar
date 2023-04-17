package racingcar.domain;

import racingcar.domain.vo.Trial;
import racingcar.dto.RacingRequest;
import racingcar.utils.Converter;
import racingcar.utils.RandomNumberGenerator;

public class RacingGame {

    public static Cars run(RacingRequest racingRequest) {
        Cars cars = Cars.initialize(racingRequest.getNames(), RandomNumberGenerator.makeInstance());
        Trial trial = getTrial(racingRequest.getCount());
        return playGame(cars, trial);
    }

    public static Trial getTrial(String input) {
        return Trial.of(Converter.convertStringToInt(input));
    }

    private static Cars playGame(Cars cars, Trial trial) {
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
        }
        return cars;
    }

}
