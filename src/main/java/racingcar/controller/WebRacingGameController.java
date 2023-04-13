package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.service.RacingGameService;

@RestController
public class WebRacingGameController {
    private final RacingGameService racingGameService;

    public WebRacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public GameResponse plays(@RequestBody final GameRequest gameRequest) {
        return racingGameService.play(gameRequest);
    }
}
