package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.controller.validator.GameOptionValidator;
import racingcar.service.RaceService;

@RestController
public class RacingCarWebController {

    private final GameOptionValidator gameOptionValidator;
    private final RaceService raceService;

    public RacingCarWebController(final GameOptionValidator gameOptionValidator,
                                  final RaceService raceService) {
        this.gameOptionValidator = gameOptionValidator;
        this.raceService = raceService;
    }

    @PostMapping("/plays")
    public RaceResultResponse registerRaceResult(@RequestBody final GameInfoRequest gameInfoRequest) {
        gameOptionValidator.validateGameOption(gameInfoRequest);
        int savedRaceResultId = raceService.saveRaceResult(gameInfoRequest);
        return raceService.createRaceResult(savedRaceResultId);
    }
}
