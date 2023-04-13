package racingcar.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.service.RaceResultService;

@RestController
public class RacingCarWebController {

    private final RaceResultService raceResultService;

    public RacingCarWebController(final RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }

    @PostMapping("/plays")
    public RaceResultResponse registerRaceResult(@Validated @RequestBody final GameInfoRequest gameInfoRequest) {
        return raceResultService.createRaceResult(gameInfoRequest);
    }
}
