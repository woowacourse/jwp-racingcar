package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.PlaysRequest;
import racingcar.controller.dto.PlaysResponse;
import racingcar.service.RacingCarService;

import java.util.List;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlaysResponse> play(@RequestBody PlaysRequest playsRequest) {
        PlaysResponse response = racingCarService.play(playsRequest);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlaysResponse>> getGames() {
        List<PlaysResponse> response = racingCarService.getGamesAll();
        return ResponseEntity.ok().body(response);
    }
}
