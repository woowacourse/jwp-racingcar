package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.service.RacingService;

@RestController
public class WebController {

    private final RacingService racingService;

    @Autowired
    public WebController(final RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public PlayResponse plays(@RequestBody final PlayRequest playRequest) {
        return racingService.playRacing(playRequest);
    }
}
