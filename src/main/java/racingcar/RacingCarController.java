package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RacingCarController {

    private final RacingGameService racingGameService;

    public RacingCarController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @GetMapping("/plays")
    @ResponseBody
    public ResponseEntity<List<GameResultResponseDto>> history() {
        final List<GameResultResponseDto> history = racingGameService.getHistory();
        return ResponseEntity.ok(history);
    }

    @PostMapping("/plays")
    @ResponseBody
    public ResponseEntity<GameResultResponseDto> racingGame(@RequestBody UserRequestDto inputDto) {
        final GameResultResponseDto gameResultResponseDto = racingGameService.getResult(inputDto);
        return ResponseEntity.ok(gameResultResponseDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> hello(RuntimeException exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.badRequest().build();
    }
}
