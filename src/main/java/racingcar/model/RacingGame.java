package racingcar.model;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.service.RandomMaker;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/19
 */
public class RacingGame {

    private int id;
    private final List<Car> cars;
    private final int trialCount;

    public RacingGame(final List<Car> cars, final int trialCount) {
        this.cars = cars;
        this.trialCount = trialCount;
    }

    public RacingGame(final int id, final List<Car> cars, final int trialCount) {
        this.id = id;
        this.cars = cars;
        this.trialCount = trialCount;
    }

    public void moveAllCars() {
        for (Car car : cars) {
            car.move(RandomMaker.random());
        }
    }

    public List<Car> findWinners() {
        int winnerPosition = findPosition();

        return cars.stream()
                .filter(car -> car.isPosition(winnerPosition))
                .collect(Collectors.toUnmodifiableList());
    }

    private int findPosition() {
        int maxPosition = 0;

        for (Car car : cars) {
            maxPosition = car.findGreaterPosition(maxPosition);
        }

        return maxPosition;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
