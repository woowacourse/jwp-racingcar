package racingcar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.service.RaceService;

@RestController
public class RacingCarWebController {

    private final RaceService raceService;

    public RacingCarWebController(final RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping("/plays")
    public RaceResultResponse registerRaceResult(@Valid  @RequestBody final GameInfoRequest gameInfoRequest) {
        int savedRaceResultId = raceService.saveRaceResult(gameInfoRequest);
        return raceService.createRaceResult(savedRaceResultId);
    }

    @GetMapping("/plays")
    public List<RaceResultResponse> searchAllRaceResult() {
        return raceService.searchAllRaceResult();
    }
}
