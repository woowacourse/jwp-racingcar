package racingcar.controller;

import org.springframework.web.bind.annotation.*;
import racingcar.dto.RacingCarResponse;
import racingcar.dto.RacingGameRequest;
import racingcar.service.RacingCarService;
import racingcar.util.NumberGenerator;

import java.util.List;

@RestController
@RequestMapping("plays")
public class RacingCarWebController {

    private final RacingCarService racingCarService;
    private final NumberGenerator numberGenerator;

    public RacingCarWebController(RacingCarService racingCarService, NumberGenerator numberGenerator) {
        this.racingCarService = racingCarService;
        this.numberGenerator = numberGenerator;
    }

    @PostMapping
    public RacingCarResponse createGame(@RequestBody RacingGameRequest racingGameRequest) {
        return racingCarService.play(racingGameRequest, numberGenerator);
    }

    @GetMapping
    public List<RacingCarResponse> loadAllGame() {
        return racingCarService.findAllGame();
    }

    @ExceptionHandler
    public void handle(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
