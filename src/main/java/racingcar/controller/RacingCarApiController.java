package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.RacingCarRequestDto;
import racingcar.dto.response.RacingGameResponseDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarApiController {

    private final RacingCarService racingCarService;

    public RacingCarApiController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public RacingGameResponseDto play(@RequestBody RacingCarRequestDto racingCarRequestDto) {
        return racingCarService.play(racingCarRequestDto);
    }
}
