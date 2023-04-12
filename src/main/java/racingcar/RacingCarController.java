package racingcar;

import org.springframework.http.ResponseEntity;
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
        PlayResponse response = racingCarWebService.play(playRequest);
        return ResponseEntity.ok().body(response);
    }
}
