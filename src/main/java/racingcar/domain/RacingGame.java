package racingcar.domain;

import java.util.List;

public class RacingGame {

    private static final String ROUND_NUMBER_ERROR = "시도 횟수는 1이상이어야 합니다.";
    private static final int ROUND_MIN_NUM = 1;

    private final Cars cars;
    private final int totalRound;
    private final NumberGenerator numberGenerator = new RandomNumberGenerator();

    public RacingGame(final Cars cars, final int totalRound) {
        validateRound(totalRound);
        this.cars = cars;
        this.totalRound = totalRound;
    }

    private void validateRound(final int totalRound) {
        if (totalRound < ROUND_MIN_NUM) {
            throw new IllegalArgumentException(ROUND_NUMBER_ERROR);
        }
    }

    public void play() {
        for (int i = 0; i < totalRound; i++) {
            cars.move(numberGenerator);
        }
    }

    public List<Car> findWinnerCars() {
        return cars.findAllWinner();
    }

    public List<Car> getCars() {
        return cars.getCars();
    }

    public int getTotalRound() {
        return totalRound;
    }
}
