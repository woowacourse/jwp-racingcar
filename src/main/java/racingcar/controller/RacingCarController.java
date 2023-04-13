package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
    public GameResponse play(@Valid @RequestBody GameRequest gameRequest) {
        int gameId = racingCarService.play(gameRequest);
        List<CarDto> winners = racingCarService.findWinners(gameId);
        String winnerNames = winners.stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(DELIMITER));
        List<CarDto> cars = racingCarService.findCars(gameId);

        return GameResponse.of(winnerNames, cars);
    }
}
