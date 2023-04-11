package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

@Service
public class RacingGameService {

    public RacingGameResponse play(RacingGameRequest racingGameRequest) {
        Cars cars = new Cars(racingGameRequest.getNames().stream()
                .map(Car::new)
                .collect(Collectors.toList()));
        RacingGame game = new RacingGame(racingGameRequest.getCount(), cars);
        while (!game.isEnd()) {
            game.playOneRound();
        }
        return getResult(game);
    }

    private RacingGameResponse getResult(RacingGame racingGame) {
        Cars cars = racingGame.getCars();
        List<CarDto> racingCars = cars.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
        return new RacingGameResponse(getWinnerNames(cars), racingCars);
    }

    private List<String> getWinnerNames(Cars cars) {
        return cars.findWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
