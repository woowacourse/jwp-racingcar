package racingcar.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.api.dto.request.CarGameRequest;
import racingcar.api.dto.response.GameResponse;
import racingcar.service.RacingGameService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebRacingCarController {

    private final RacingGameService service;

    public WebRacingCarController(RacingGameService service) {
        this.service = service;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResponse>> getAllPlays() {
        List<GameResponse> result = service.findAllGame();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> plays(@Valid @RequestBody CarGameRequest request) {
        GameResponse result = service.play(request);
        return ResponseEntity.ok(result);
    }
}
