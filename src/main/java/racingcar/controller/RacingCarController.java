package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
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

    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public GameResponse createGame(@RequestBody GameRequest gameRequest) {
        String names = gameRequest.getNames();
        List<String> carNames = List.of(names.split(","));

        racingCarService.createGame(carNames);
        racingCarService.race(gameRequest.getCount());
        List<String> winners = racingCarService.findWinners();
        String winnerNames = String.join(",", winners);

        List<CarDto> carDtos = racingCarService.findCars().stream()
                .map(car -> CarDto.from(car))
                .collect(Collectors.toList());

        return GameResponse.of(winnerNames, carDtos);
    }
}
