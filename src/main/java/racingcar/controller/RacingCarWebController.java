package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.GameInforamtionDto;
import racingcar.domain.GameResultDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarWebController {

    private final RacingCarService racingCarService;

    public RacingCarWebController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("plays")
    public GameResultDto createGame(@RequestBody GameInforamtionDto gameInforamtionDto) {
        return racingCarService.play(gameInforamtionDto);
    }
}
