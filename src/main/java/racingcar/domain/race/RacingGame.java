package racingcar.domain.race;

import java.util.List;
import racingcar.domain.car.Car;

public class RacingGame {
    private final RacingCars racingCars;
    private WinnerJudge winnerJudge;

    public RacingGame(List<String> carNames, WinnerJudge winnerJudge) {
        this.racingCars = new RacingCars(carNames);
        this.winnerJudge = winnerJudge;
    }

    public void progress(int trialCount) {
        for (int count = 0; count < trialCount; count++) {
            racingCars.moveCars();
        }
    }

    public boolean isWinner(Car car) {
        List<Car> winners = winnerJudge.getWinner(racingCars.getCars()); // TODO: 2023/04/13 winner 여부를 한 번만 연산하도록 변경
        return winners.stream().anyMatch(winner -> winner.getName().equals(car.getName()));
    }

    public List<Car> getWinners() {
        return winnerJudge.getWinner(racingCars.getCars());
    }

    public List<Car> getRacingCars() {
        return racingCars.getCars();
    }

}
