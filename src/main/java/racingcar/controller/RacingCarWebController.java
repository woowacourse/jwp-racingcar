package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarWebController {

    private final RacingCarService racingCarService;

    public RacingCarWebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultResponseDto> play(@RequestBody RacingGameRequestDto racingGameRequestDto) {
        ResultResponseDto playResult = racingCarService.play(racingGameRequestDto);
        return ResponseEntity.ok(playResult);
    }
}
