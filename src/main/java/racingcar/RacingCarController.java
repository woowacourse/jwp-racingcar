package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayResponse> play(@RequestBody final PlayRequest playRequest) {
        PlayResponse response = racingCarService.play(playRequest);
        return ResponseEntity.ok().body(response);
    }
}
