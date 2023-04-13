package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.GameRequestDto;
import racingcar.dto.response.GameResponseDto;
import racingcar.service.RacingGameService;

@RestController
public class WebRacingGameController {
    private final RacingGameService racingGameService;

    @Autowired
    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponseDto> playGame(@RequestBody GameRequestDto request) {
        GameResponseDto response = racingGameService.play(request.getNames(), request.getCount());

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler
    public ResponseEntity handleException(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
