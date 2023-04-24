package racingcar.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInitializationRequest;
import racingcar.dto.GameResultResponse;
import racingcar.service.RacingCarGameService;
import racingcar.service.SearchingGameHistoryService;

@RestController
public class RacingCarWebController {

    private final RacingCarGameService racingCarGameService;
    private final SearchingGameHistoryService searchingGameHistoryService;

    public RacingCarWebController(final RacingCarGameService racingCarGameService,
                                  final SearchingGameHistoryService searchingGameHistoryService) {
        this.racingCarGameService = racingCarGameService;
        this.searchingGameHistoryService = searchingGameHistoryService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponse> playRacingCar(
            @RequestBody @Valid GameInitializationRequest gameInitializationRequest) {
        final GameResultResponse gameResultResponse = racingCarGameService.raceCar(gameInitializationRequest);
        return ResponseEntity.ok().body(gameResultResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResultResponse>> searchGameHistory() {
        final List<GameResultResponse> gameResultResponses = searchingGameHistoryService.searchGameHistory();
        return ResponseEntity.ok().body(gameResultResponses);
    }
}
