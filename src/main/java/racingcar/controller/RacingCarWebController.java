package racingcar.controller;

import org.springframework.web.bind.annotation.*;
import racingcar.dto.GameInforamtionDto;
import racingcar.dto.GameResultDto;
import racingcar.service.RacingCarService;
import racingcar.util.NumberGenerator;

import java.util.List;

@RestController
public class RacingCarWebController {

    private final RacingCarService racingCarService;
    private final NumberGenerator numberGenerator;

    public RacingCarWebController(RacingCarService racingCarService, NumberGenerator numberGenerator) {
        this.racingCarService = racingCarService;
        this.numberGenerator = numberGenerator;
    }

    @PostMapping("plays")
    public GameResultDto createGame(@RequestBody GameInforamtionDto gameInforamtionDto) {
        return racingCarService.play(gameInforamtionDto, numberGenerator);
    }

    @GetMapping("plays")
    public List<GameResultDto> loadAllGame() {
        return racingCarService.findAllGame();
    }

    @ExceptionHandler
    public void handle(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
