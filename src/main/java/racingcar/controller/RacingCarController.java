package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.HistoryResponse;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<HistoryResponse>> getGameHistory() {
        return ResponseEntity.ok().body(racingCarService.getHistory());
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayResponse> play(@RequestBody final PlayRequest playRequest) {
        PlayResponse response = racingCarService.play(playRequest);
        return ResponseEntity.ok().body(response);
    }
}
