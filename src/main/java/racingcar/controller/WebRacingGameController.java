package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInfoDto;
import racingcar.dto.GameResultResponseDto;
import racingcar.service.RacingGameService;

@RestController
public class WebRacingGameController {
    private final RacingGameService racingGameService;

    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponseDto> playGame(@RequestBody GameInfoDto request) {
        GameResultResponseDto response = racingGameService.play(request.getNames(), request.getCount());

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler
    public ResponseEntity handleException(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
