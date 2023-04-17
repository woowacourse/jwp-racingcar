package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.service.RacingGameService;

@RestController
public class RacingWebController {
    private final RacingGameService racingGameService;

    public RacingWebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays")
    public GameResponseDto play(@RequestBody GameRequestDto gameRequestDto) {
        return racingGameService.play(gameRequestDto.getNames(), gameRequestDto.getCount());
    }
}
