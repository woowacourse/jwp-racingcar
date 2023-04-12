package racingcar.domain.race;

import java.time.LocalDateTime;
import java.util.List;
import racingcar.domain.car.Car;

public class RacingGame {
    private final RacingCars racingCars = new RacingCars();
    private final int trialCount;
    private final LocalDateTime playTime;
    private WinnerJudge winnerJudge;

    public RacingGame(List<String> carNames, WinnerJudge winnerJudge, int trialCount) {
        for (String carName : carNames) {
            Car newCar = new Car(carName);
            racingCars.add(newCar);
        }
        this.trialCount = trialCount;
        this.playTime = LocalDateTime.now();
        this.winnerJudge = winnerJudge;
    }

    public void tryMoveOneTime() {
        racingCars.moveCars();
    }

    public List<Car> getWinners() {
        return winnerJudge.getWinner(racingCars.getCars());
    }

    public List<Car> getRacingCars() {
        return racingCars.getCars();
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getPlayTime() {
        return playTime;
    }
}
