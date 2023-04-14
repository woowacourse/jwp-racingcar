package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RacingCarController {

    private final RacingGameService racingGameService;

    public RacingCarController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public ResponseEntity<GameResultResponseDto> racingGame(@RequestBody UserRequestDto inputDto) {
        final GameResultResponseDto gameResultResponseDto = racingGameService.getResult(inputDto);
        return ResponseEntity.ok(gameResultResponseDto);
    }

}
