package racingcar.controller;

import java.net.URI;
import java.util.List;
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
public class RacingGameWebController {
    private final RacingGameAddService racingGameAddService;
    private final RacingGameFindService racingGameFindService;

    public RacingGameWebController(RacingGameAddService racingGameAddService,
                                   RacingGameFindService racingGameFindService) {
        this.racingGameAddService = racingGameAddService;
        this.racingGameFindService = racingGameFindService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> play(@RequestBody RacingGameRequest racingGameRequest) {
        RacingGameResponse racingGameResponse = racingGameAddService.addGame(racingGameRequest);
        return ResponseEntity.created(URI.create("/plays/" + racingGameResponse.getGameId())).body(racingGameResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResponse>> playHistory() {
        List<RacingGameResponse> history = racingGameFindService.findHistory();
        return ResponseEntity.ok(history);
    }
}
