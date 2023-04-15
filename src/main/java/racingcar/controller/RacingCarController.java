package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResponseDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayResponseDto> play(@RequestBody PlayRequestDto playRequestDto) {
        PlayResponseDto playResult = racingCarService.playGame(playRequestDto);
        return ResponseEntity.ok(playResult);
    }

}
