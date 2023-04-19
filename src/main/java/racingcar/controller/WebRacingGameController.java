package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.GameResponse;
import racingcar.service.RacingGameService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebRacingGameController {
    private final RacingGameService racingGameService;

    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> plays(@RequestBody @Valid CarGameRequest carGameRequest) {
        GameResponse result = racingGameService.play(carGameRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResponse>> findAllCarGame() {
        return ResponseEntity.ok(racingGameService.findAllCarGame());
    }
}
