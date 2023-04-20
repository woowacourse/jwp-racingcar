package racingcar.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.request.RacingGameRequest;
import racingcar.controller.response.RacingGameResponse;
import racingcar.service.RacingGameService;

import java.util.List;

@RestController
public class RacingGameWebController {
    private final RacingGameService racingGameService;

    public RacingGameWebController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> playRacingGame(@RequestBody RacingGameRequest request) {
        final List<String> nameValues = List.of(request.getNames().split(","));
        final RacingGameResponse response = racingGameService.race(nameValues, request.getCount());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResponse>> getRacingGameHistories() {
        final List<RacingGameResponse> response = racingGameService.findAllRacingGameHistories();

        return ResponseEntity.ok(response);
    }
}
