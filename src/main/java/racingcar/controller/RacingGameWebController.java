package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GameRequest;
import racingcar.controller.dto.GameResponse;
import racingcar.service.RacingGameService;

@RestController
@RequestMapping("/plays")
public class RacingGameWebController {

    private final RacingGameService racingGameService;

    public RacingGameWebController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping
    public ResponseEntity<GameResponse> doGame(@RequestBody final GameRequest gameRequest) {
        final GameResponse response = racingGameService.run(gameRequest);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> findResult() {
        final List<GameResponse> responses = racingGameService.findAll();

        return ResponseEntity.ok(responses);
    }
}
