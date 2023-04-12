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

@RestController
public class RacingCarController {

    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public GameResponse createGame(@Valid @RequestBody GameRequest gameRequest) {
        List<String> carNames = splitNames(gameRequest);
        racingCarService.createGame(carNames);

        racingCarService.race(gameRequest.getCount());
        List<String> winners = racingCarService.findWinners();
        String winnerNames = String.join(DELIMITER, winners);
        List<CarDto> cars = convertDto(racingCarService.findCars());

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
