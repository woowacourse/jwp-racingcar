package racingcar.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.CarGameResponse;
import racingcar.service.RacingGameService;

import javax.validation.Valid;

@RestController
public class WebRacingCarController {

    private final RacingGameService racingGameService;

    public WebRacingCarController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<CarGameResponse> plays(@Valid @RequestBody CarGameRequest request) {
        CarGameResponse result = racingGameService.play(request);
        return ResponseEntity.ok(result);
    }
}
