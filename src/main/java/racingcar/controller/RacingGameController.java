package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.service.RacingGameServiceImpl;

import java.util.Arrays;
import java.util.List;

@RestController
public class RacingGameController {

    private final RacingGameServiceImpl racingGameService;

    public RacingGameController(final RacingGameServiceImpl racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    GameResultDto play(
            @RequestBody PlayRequestDto playRequestDto
    ) {
        List<String> names = Arrays.asList(playRequestDto.getNames().split(","));
        return racingGameService.play(names, playRequestDto.getCount());
    }
}
