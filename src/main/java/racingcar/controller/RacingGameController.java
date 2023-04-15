package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;
import racingcar.service.RacingGameService;
import racingcar.view.OutputView;

import java.util.List;

@RestController
public class RacingGameController {
    private final RacingGameService racingGameService;

    public RacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> play(@RequestBody RacingGameRequest racingGameRequest) {
        RacingGameResponse response = racingGameService.play(racingGameRequest);
        showResult(response);
        return ResponseEntity.ok(response);
    }

    private void showResult(RacingGameResponse response) {
        OutputView.printWinners(response.getWinners());
        OutputView.printRacing(response.getRacingCars());
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResponse>> getHistory() {
        List<RacingGameResponse> histories = racingGameService.getHistory();
        return ResponseEntity.ok(histories);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
