package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RacingCarController {
    private final RacingCarWebService racingCarWebService;

    public RacingCarController(final RacingCarWebService racingCarWebService) {
        this.racingCarWebService = racingCarWebService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayResponse> play(@RequestBody PlayRequest playRequest) {
        final RacingCarGameDto racingCarGameDto = new RacingCarGameDto(playRequest);
        PlayResponse response = racingCarWebService.play(racingCarGameDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/plays")
    public void showGameHistory() {
        racingCarWebService.gameHistory();
    }
}
