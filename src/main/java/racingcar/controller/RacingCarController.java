package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInfoRequest;
import racingcar.dto.GameResultResponse;
import racingcar.exception.PlayerNumberException;
import racingcar.exception.PlayerSizeException;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarController {
    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponse> play(@RequestBody GameInfoRequest gameInfoRequest) {
        GameResultResponse gameResultResponse = racingCarService.createResponse(gameInfoRequest);
        return ResponseEntity.ok().body(gameResultResponse);
    }

    @ExceptionHandler({PlayerNumberException.class, PlayerSizeException.class})
    public ResponseEntity<String> handlePlayerNumber(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
