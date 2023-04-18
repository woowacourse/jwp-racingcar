package racingcar.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameResultDto;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;
import racingcar.service.RacingGameService;

@RestController
public final class RacingController {

    private final RacingGameService racingGameService;

    @Autowired
    public RacingController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays")
    public RacingGameResponse playRacingGame(
            @Valid @RequestBody final RacingGameRequest racingGameRequest) {
        GameResultDto gameResultDto = this.racingGameService.playRacingGame(racingGameRequest);
        return RacingGameResponse.from(gameResultDto);
    }
}
