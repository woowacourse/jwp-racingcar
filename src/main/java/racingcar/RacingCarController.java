package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RacingCarController {

    final RacingGameService racingGameService;

    public RacingCarController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @GetMapping("/racing-game")
    @ResponseBody
    public ResponseEntity<ResultDto> racingGame(@RequestBody UserInputDto inputDto) {
        final ResultDto resultDto = racingGameService.getResult(inputDto);

        return ResponseEntity.ok(resultDto);
    }
}
