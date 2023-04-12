package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.service.RacingCarService;
import racingcar.service.RacingGame;

@RestController
public class RacingCarController {

    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public GameResponse play(@Valid @RequestBody GameRequest gameRequest) {
        List<String> carNames = splitNames(gameRequest);
        RacingGame game = racingCarService.createGame(carNames, gameRequest.getCount());

        racingCarService.race(game, gameRequest.getCount());
        racingCarService.updateWinners(game);
        List<String> winners = racingCarService.findWinners(game);
        String winnerNames = String.join(DELIMITER, winners);
        List<CarDto> cars = convertDto(racingCarService.findCars(game));

        return GameResponse.of(winnerNames, cars);
    }

    private List<CarDto> convertDto(List<Car> cars) {
        return cars.stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
    }

    private List<String> splitNames(final GameRequest gameRequest) {
        String names = gameRequest.getNames();

        return List.of(names.split(DELIMITER));
    }
}
