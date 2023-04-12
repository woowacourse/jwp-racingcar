package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.controller.validator.GameOptionValidator;
import racingcar.service.RaceResultService;

@RestController
public class RacingCarWebController {

    private final GameOptionValidator gameOptionValidator;
    private final RaceResultService raceResultService;

    public RacingCarWebController(final GameOptionValidator gameOptionValidator,
                                  final RaceResultService raceResultService) {
        this.gameOptionValidator = gameOptionValidator;
        this.raceResultService = raceResultService;
    }

    @PostMapping("/plays")
    public RaceResultResponse registerRaceResult(@RequestBody final GameInfoRequest gameInfoRequest) {

        gameOptionValidator.validateGameOption(gameInfoRequest);

        return raceResultService.createRaceResult(gameInfoRequest);
    }
}
