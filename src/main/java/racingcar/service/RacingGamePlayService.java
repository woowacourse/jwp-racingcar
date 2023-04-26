package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

@Service
public class RacingGamePlayService {
    private final RacingGameAddService racingGameAddService;

    public RacingGamePlayService(RacingGameAddService racingGameAddService) {
        this.racingGameAddService = racingGameAddService;
    }

    public RacingGameResponse play(RacingGameRequest racingGameRequest) {
        List<Car> cars = racingGameRequest.getNames().stream()
                .map(Car::new)
                .collect(Collectors.toList());
        RacingGame racingGame = new RacingGame(racingGameRequest.getCount(), new Cars(cars));
        racingGame.run();
        return racingGameAddService.addGame(racingGame, racingGameRequest.getCount());
    }
}
