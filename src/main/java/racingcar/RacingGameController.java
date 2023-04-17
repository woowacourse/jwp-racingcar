package racingcar;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.ResultDto;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultDto> play(@RequestBody RacingGameRequest request) {
        ResultDto result = racingGameService.start(request.getCount(), request.getNames());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<ResultDto>> viewAllGames() {
        return ResponseEntity.ok(racingGameService.findAllGameHistories());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleTryTimeException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
