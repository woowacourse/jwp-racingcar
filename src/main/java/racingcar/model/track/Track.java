package racingcar.model.track;

import racingcar.exception.CustomException;
import racingcar.model.TrialTimes;
import racingcar.model.car.Cars;
import racingcar.model.car.strategy.MovingStrategy;

import java.util.regex.Pattern;

public class Track {

    private final Cars cars;
    private final TrialTimes trialTimes;
    private final MovingStrategy movingStrategy;

    private Track(final Cars cars, final TrialTimes trialTimes, final MovingStrategy movingStrategy) {
        this.cars = cars;
        this.trialTimes = trialTimes;
        this.movingStrategy = movingStrategy;
    }

    public static Track of(final Cars cars, final TrialTimes trialTimes, final MovingStrategy movingStrategy) {
        return new Track(cars, trialTimes, movingStrategy);
    }

    public Cars race() {
        cars.moveCars(movingStrategy);
        trialTimes.use();
        return cars;
    }

    public boolean runnable() {
        return trialTimes.runnable();
    }

    public Cars getCars() {
        return cars;
    }

    public TrialTimes getTrialTimes() {
        return trialTimes;
    }
}
