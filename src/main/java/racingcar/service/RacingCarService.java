package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.strategy.RandomMovingStrategy;

@Service
public class RacingCarService {

    private RacingGame racingGame;

    public void createGame(List<String> carNames) {
        racingGame = new RacingGame(CarFactory.buildCars(carNames), new RandomMovingStrategy());
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
