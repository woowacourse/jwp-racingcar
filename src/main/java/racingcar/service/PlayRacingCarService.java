package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.RacingCarGame;
import racingcar.utils.RandomNumberGenerator;

@Service
public class PlayRacingCarService {

    public RacingCarResult playRacingCar(final List<String> names, final int attempt) {
        final RacingCarGame racingCarGame = new RacingCarGame(names, attempt, new RandomNumberGenerator());
        final List<String> winners = racingCarGame.findWinners();
        final List<Car> cars = racingCarGame.getCars();
        return new RacingCarResult(winners, cars, attempt);
    }
}
