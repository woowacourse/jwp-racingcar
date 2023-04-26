package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    public static final int MAX_TRY_COUNT_BOUND = 100;

    private final NumberGenerator numberGenerator;
    private final Cars cars;
    private int tryCount;

    public RacingGame(NumberGenerator numberGenerator, int tryCount, Cars cars) {
        validateTryCount(tryCount);
        this.numberGenerator = numberGenerator;
        this.cars = cars;
        this.tryCount = tryCount;
    }

    public RacingGame(int tryCount, Cars cars) {
        this(new RandomNumberGenerator(), tryCount, cars);
    }

    public static RacingGame of(List<String> names, int tryCount) {
        List<Car> cars = names.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new RacingGame(tryCount, new Cars(cars));
    }

    private void validateTryCount(int tryCount) {
        if (tryCount > MAX_TRY_COUNT_BOUND) {
            throw new IllegalArgumentException("시도 횟수는 100회 이하만 가능합니다 현재 : " + tryCount + "회");
        }
    }

    public void run() {
        while (isNotEnd()) {
            playOneRound();
        }
    }

    private void playOneRound() {
        cars.moveAll(numberGenerator);
        tryCount--;
    }

    private boolean isNotEnd() {
        return tryCount != 0;
    }

    public List<Car> findWinners() {
        return cars.findWinners();
    }

    public List<Car> getCars() {
        return cars.getCars();
    }
}
