package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.dto.GameResultDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.service.RacingGameService;

import javax.validation.Valid;

@Controller
public final class RacingController {

    private final RacingGameService racingGameService;

    @Autowired
    public RacingController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays")
    @ResponseBody
    public GameResultDto playRacingGame(@Valid @RequestBody final RacingGameRequestDto racingGameRequestDto) {
        return racingGameService.playRacingGame(racingGameRequestDto);
    }
}
