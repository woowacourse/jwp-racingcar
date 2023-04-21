package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.GameInfoRequest;
import racingcar.dto.GameResultResponse;
import racingcar.dto.PlayRecordsResponse;
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
    public ResponseEntity<GameResultResponse> createCarsAndGameRecords(@RequestBody GameInfoRequest gameInfoRequest) {
        GameResultResponse gameResultResponse = racingCarService.createResponse(gameInfoRequest);
        return ResponseEntity.created(URI.create("/plays")).body(gameResultResponse);
    }

    @GetMapping("/plays")
    public List<PlayRecordsResponse> showPlayRecords() {
        return racingCarService.showPlayRecords();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
