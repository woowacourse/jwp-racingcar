package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.strategy.MovingStrategy;

@Service
public class RacingCarService {

    private final MovingStrategy movingStrategy;
    private RacingGame racingGame;

    public RacingCarService(final MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public void createGame(List<String> carNames) {
        racingGame = new RacingGame(CarFactory.buildCars(carNames), movingStrategy);
    }

    public void race(int tryTimes) {
        for (int i = 0; i < tryTimes; i++) {
            racingGame.playSingleRound();
        }
    }

    public List<String> findWinners() {
        return racingGame.getWinners();
    }

    public List<Car> findCars() {
        return racingGame.getCars();
    }
}
