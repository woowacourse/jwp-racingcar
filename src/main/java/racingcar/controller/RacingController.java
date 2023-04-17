package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameResultResponse;
import racingcar.dto.RacingGameRequest;
import racingcar.service.RacingGameService;

import javax.validation.Valid;
import java.util.List;

@RestController
public final class RacingController {

    private final RacingGameService racingGameService;

    @Autowired
    public RacingController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays")
    public ResponseEntity<GameResultResponse> playRacingGame(
            @Valid @RequestBody final RacingGameRequest racingGameRequest
    ) {
        return ResponseEntity.ok(racingGameService.playRacingGame(racingGameRequest));
    }

    @GetMapping(path = "/plays")
    public ResponseEntity<List<GameResultResponse>> readRecords() {
        return ResponseEntity.ok(racingGameService.makeGameRecords());
    }
}
