package racingcar.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.CarGameResponse;
import racingcar.service.RacingGameService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebRacingCarController {

    private final RacingGameService racingGameService;

    public WebRacingCarController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<CarGameResponse>> getAllPlays() {
        List<CarGameResponse> result = racingGameService.findAllGame();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/plays")
    public ResponseEntity<CarGameResponse> plays(@Valid @RequestBody CarGameRequest request) {
        CarGameResponse result = racingGameService.play(request);
        return ResponseEntity.ok(result);
    }
}
