package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.RacingCarResponse;

public class GameManager {
    private final NumberGenerator numberGenerator;
    private final Cars cars;
    private final GameRound gameRound;

    public GameManager(final Cars cars, final GameRound gameRound, final NumberGenerator numberGenerator) {
        this.cars = cars;
        this.gameRound = gameRound;
        this.numberGenerator = numberGenerator;
        play();
    }

    public void play() {
        while (isPlayable()) {
            playGameRound();
        }
    }

    private void playGameRound() {
        List<Car> currentCars = cars.getCars();
        for (Car car : currentCars) {
            car.move(numberGenerator.generateNumber());
        }
        gameRound.increaseRound();
    }

    private boolean isPlayable() {
        return gameRound.isPlayable();
    }

    public List<String> decideWinner() {
        return cars.findWinnerNames();
    }

    public List<RacingCarResponse> getResultCars() {
        return cars.getCars()
                .stream()
                .map(it -> new RacingCarResponse(it.getName(), it.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }
}
