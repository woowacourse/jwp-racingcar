package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.GameResultResponseDto;
import racingcar.dto.UserRequestDto;
import racingcar.service.RacingGameService;

@Controller
public class WebController {

    private final RacingGameService racingGameService;

    public WebController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    /*@GetMapping("/plays")
    public ResponseEntity<List<GameResultResponseDto>> history() {
        final List<GameResultResponseDto> history = racingGameService.getHistory();
        return ResponseEntity.ok(history);
    }*/

    @PostMapping("/plays")
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
