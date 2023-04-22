package racing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racing.controller.dto.response.GameInfoResponse;
import racing.util.RandomNumberGenerator;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.controller.dto.response.RacingGameResultResponse;
import racing.service.RacingGameService;

import java.util.List;


@RestController
public class WebRacingGameController {

    private final RacingGameService racingGameService;
    private final RandomNumberGenerator generator;

    public WebRacingGameController(RacingGameService racingGameService, RandomNumberGenerator generator) {
        this.racingGameService = racingGameService;
        this.generator = generator;
    }

    @PostMapping("/plays")
    public RacingGameResultResponse start(@RequestBody RacingGameInfoRequest request) {
        GameInfoResponse response = racingGameService.executeWebGame(request);
        return racingGameService.saveCarsState(response.getGameId(), response.getCars());
    }

    @GetMapping("/plays")
    public List<RacingGameResultResponse> getResult() {
        return racingGameService.getRacingGameResultResponse();
    }

}
