package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.RacingGameService;

@RestController
public class RacingGameWebController {

    private final RacingGameService racingGameService;

    public RacingGameWebController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public RacingGameResponseDto run(@RequestBody RacingGameRequestDto racingGameRequestDto) {
        return racingGameService.run(racingGameRequestDto);
    }
}
