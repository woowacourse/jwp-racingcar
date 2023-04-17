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
        validateTryTime(request.getCount());
        ResultDto result = racingGameService.start(request.getCount(), splitNames(request.getNames()));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<ResultDto>> viewAllGames() {
        return ResponseEntity.ok(racingGameService.findAllGameHistories());
    }

    private List<String> splitNames(String names) {
        String regex = ",";
        return List.of(names.split(regex));
    }

    private void validateTryTime(int tryTime) {
        if (tryTime < 0) {
            throw new IllegalArgumentException("시도 횟수는 음수일 수 없습니다.");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleTryTimeException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
