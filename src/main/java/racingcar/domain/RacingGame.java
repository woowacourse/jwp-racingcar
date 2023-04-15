package racingcar.domain;

import java.util.List;

public class RacingGame {

    private static final String ROUND_NUMBER_ERROR = "[ERROR] 시도 횟수는 1이상이어야 합니다.";
    private static final int ROUND_MIN_NUM = 1;

    private final Cars cars;
    private final int totalRound;

    public RacingGame(final Cars cars, int totalRound){
        validateRound(totalRound);
        this.cars = cars;
        this.totalRound = totalRound;
    }

    private void validateRound(int totalRound) {
        if (totalRound < ROUND_MIN_NUM) {
            throw new IllegalArgumentException(ROUND_NUMBER_ERROR);
        }
    }

    public void play(NumberGenerator numberGenerator) {
        for (int i = 0; i < totalRound; i++) {
            cars.move(numberGenerator);
        }
    }

    public List<Car> findWinnerCars(){
        return cars.findAllWinner();
    }

    public List<Car> getCars() {
        return cars.getCars();
    }
}
