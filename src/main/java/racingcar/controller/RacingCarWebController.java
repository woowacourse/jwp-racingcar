package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.GameInfoForRequest;
import racingcar.dto.GameResultForResponse;
import racingcar.dto.PlayRecordsForResponse;
import racingcar.service.RacingCarService;

import java.net.URI;
import java.util.List;

@RestController
public class RacingCarWebController {
    private final RacingCarService racingCarService;

    public RacingCarWebController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultForResponse> createCarsAndGameRecords(@RequestBody GameInfoForRequest gameInfoForRequest) {
        GameResultForResponse gameResultForResponse = racingCarService.createResponse(gameInfoForRequest);
        return ResponseEntity.created(URI.create("/plays")).body(gameResultForResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlayRecordsForResponse>> showPlayRecords() {
        return ResponseEntity.ok(racingCarService.showPlayRecords());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
