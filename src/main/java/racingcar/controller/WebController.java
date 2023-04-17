package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.*;
import racingcar.service.RacingService;

import java.util.List;

@RestController
public class WebController {

    final private RacingService racingService;

    public WebController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public RacingResultResponse run(@RequestBody RacingRequest racingRequest) {
        Cars updatedCars = RacingGame.run(racingRequest);
        racingService.save(updatedCars);
        return new RacingResultResponse(updatedCars.getWinnerNames(), updatedCars.getCarDtos());
    }

    @GetMapping("/plays")
    public List<RacingResultResponse> run() {
        return racingService.loadHistory();
    }
}
