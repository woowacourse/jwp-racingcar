package racingcar.domain;

import java.util.List;

public class RacingGame {

    private static final int ROUND_MINIMUM_NUMBER = 1;
    private static final int ROUND_MAXIMUM_NUMBER = 100;

    private final Cars cars;
    private final int totalRound;

    public RacingGame(Cars cars, int totalRound) {
        validateRound(totalRound);
        this.cars = cars;
        this.totalRound = totalRound;
    }

    private void validateRound(int totalRound) {
        if (totalRound < ROUND_MINIMUM_NUMBER || ROUND_MAXIMUM_NUMBER < totalRound) {
            throw new IllegalArgumentException("시도 횟수는 1이상, 100 이하여야야 합니다.");
        }
    }

    public void play(NumberGenerator numberGenerator) {
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
