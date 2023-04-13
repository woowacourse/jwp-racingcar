package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.PlayRequestDto;
import racingcar.service.RacingGameService;

import java.util.Arrays;
import java.util.List;

import static racingcar.service.RacingGameService.GameResult;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    GameResult play(
            @RequestBody PlayRequestDto playRequestDto
    ) {
        List<String> names = Arrays.asList(playRequestDto.getNames().split(","));
        return racingGameService.play(names, playRequestDto.getCount());
    }
}
