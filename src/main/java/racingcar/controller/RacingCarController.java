package racingcar.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<PlayResponse> play(@RequestBody @Valid PlayRequest request) {
        PlayResponse response = racingCarService.play(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlayResponse>> play() {
        List<PlayResponse> response = racingCarService.findHistory();
        return ResponseEntity.ok(response);
    }
}
