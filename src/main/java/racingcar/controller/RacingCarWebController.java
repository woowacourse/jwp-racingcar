package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInitializationRequest;
import racingcar.dto.GameResultResponse;
import racingcar.service.RacingCarGameService;

@RestController
public class RacingCarWebController {

    private final RacingCarGameService racingCarGameService;

    public RacingCarWebController(final RacingCarGameService racingCarGameService) {
        this.racingCarGameService = racingCarGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponse> playRacingCar(
            @RequestBody GameInitializationRequest gameInitializationRequest) {
        final GameResultResponse gameResultResponse = racingCarGameService.raceCar(gameInitializationRequest);
        return ResponseEntity.ok().body(gameResultResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResultResponse>> searchGameHistory() {
        final List<GameResultResponse> gameResultResponses = racingCarGameService.searchGameHistory();
        return ResponseEntity.ok().body(gameResultResponses);
    }
}
