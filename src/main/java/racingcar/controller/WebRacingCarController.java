package racingcar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.service.WebRaceService;

@RestController
public class WebRacingCarController {

    private final WebRaceService webRaceService;

    public WebRacingCarController(final WebRaceService webRaceService) {
        this.webRaceService = webRaceService;
    }

    @PostMapping("/plays")
    public RaceResultResponse registerRaceResult(@Valid  @RequestBody final GameInfoRequest gameInfoRequest) {
        int savedRaceResultId = webRaceService.saveRaceResult(gameInfoRequest);
        return webRaceService.createRaceResult(savedRaceResultId);
    }

    @GetMapping("/plays")
    public List<RaceResultResponse> searchAllRaceResult() {
        return webRaceService.searchAllRaceResult();
    }
}
