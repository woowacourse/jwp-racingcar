package racingcar.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlaysRequest;
import racingcar.dto.PlaysResponse;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlaysResponse> play(@RequestBody @Valid PlaysRequest playsRequest) {
        PlaysResponse response = racingCarService.play(playsRequest);
        return ResponseEntity.ok().body(response);
    }
}
