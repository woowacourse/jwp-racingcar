package racingcar.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayResponse> play(@RequestBody @Valid PlayRequest playRequest) {
        PlayResponse response = racingCarService.play(playRequest);
        return ResponseEntity.ok().body(response);
    }
}
