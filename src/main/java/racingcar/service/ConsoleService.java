package racingcar.service;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RandomMoveChance;
import racingcar.domain.TrialCount;

public class ConsoleService {

    public void play(Cars cars, TrialCount trialCount) {
        playMultipleTimes(cars, trialCount);
    }

    private void playMultipleTimes(Cars cars, TrialCount trialCount) {
        for (int i = 0; i < trialCount.getTrialCount(); i++) {
            playOnce(cars);
        }
    }

    private void playOnce(Cars cars) {
        cars.moveCars(new RandomMoveChance());
    }

    public List<Car> getWinners(Cars cars) {
        return cars.getWinners();
    }

    public List<Car> getCars(Cars cars) {
        return cars.getCars();
    }
}
