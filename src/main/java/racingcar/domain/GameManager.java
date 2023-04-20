package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import racingcar.dto.CarStatusResponse;
import racingcar.dto.GameResultResponse;

public class GameManager {
    private final NumberGenerator numberGenerator;
    private final Cars cars;
    private final GameRound gameRound;

    public GameManager(final Cars cars, final GameRound gameRound, final NumberGenerator numberGenerator) {
        this.cars = cars;
        this.gameRound = gameRound;
        this.numberGenerator = numberGenerator;
    }

    public List<CarStatusResponse> playGameRound() {
        List<Car> currentCars = cars.getCars();
        for (Car car : currentCars) {
            car.move(numberGenerator.generateNumber());
        }
        gameRound.increaseRound();
        return convertCarToCarStatus(currentCars);
    }

    public boolean isEnd() {
        return gameRound.isEnd();
    }

    public GameResultResponse decideWinner() {
        return new GameResultResponse(cars.findWinnerNames());
    }

    private List<CarStatusResponse> convertCarToCarStatus(List<Car> carsStatus) {
        List<CarStatusResponse> roundResultCarStatus = new ArrayList<>();
        for (Car car : carsStatus) {
            roundResultCarStatus.add(new CarStatusResponse(car));
        }
        return roundResultCarStatus;
    }
}
