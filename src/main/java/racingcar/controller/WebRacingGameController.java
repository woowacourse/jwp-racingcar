package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.service.RacingGameService;

@RestController
public class WebRacingGameController {
    private final RacingGameService racingGameService;

    public WebRacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public GamePlayResponseDto plays(@RequestBody final GamePlayRequestDto request) {
        return racingGameService.play(request);
    }
}
