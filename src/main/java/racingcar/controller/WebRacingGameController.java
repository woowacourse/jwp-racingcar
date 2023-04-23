package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingCarResponse;
import racingcar.service.RacingGameService;

@Controller
public class WebRacingGameController {

    private final RacingGameService racingGameService;

    public WebRacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RacingCarResponse> play(@RequestBody final RacingCarRequest racingCarRequest) {

        final RacingCarResponse racingCarResponse = racingGameService.play(
                racingCarRequest.getNames(),
                racingCarRequest.getTryCount());

        return ResponseEntity.ok().body(racingCarResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(final IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
