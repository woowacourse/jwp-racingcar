package racingcar.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;
import racingcar.service.RacingGameAddService;
import racingcar.service.RacingGameFindService;

@RestController
public class RacingGameController {
    private final RacingGameAddService racingGameAddService;
    private final RacingGameFindService racingGameFindService;

    public RacingGameController(RacingGameAddService racingGameService, RacingGameFindService racingGameFindService) {
        this.racingGameAddService = racingGameService;
        this.racingGameFindService = racingGameFindService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> play(@RequestBody @Valid RacingGameRequest racingGameRequest) {
        RacingGameResponse response = racingGameAddService.play(racingGameRequest);
        return ResponseEntity.created(URI.create("/plays/" + response.getGameId())).body(response);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResponse>> playHistory() {
        List<RacingGameResponse> history = racingGameFindService.findHistory();
        return ResponseEntity.ok(history);
    }
}
