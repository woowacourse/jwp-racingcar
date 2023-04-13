package racing.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.controller.dto.response.RacingGameResultResponse;
import racing.domain.Cars;
import racing.service.RacingGameService;

import java.util.Random;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public RacingGameResultResponse start(@RequestBody RacingGameInfoRequest request) {
        Cars cars = racingGameService.createCars(request.getNames());
        Long gameId = racingGameService.createRacingGame(request.getCount());
        Random random = new Random();

        for (int i = 0; i < request.getCount(); i++) {
            racingGameService.move(random.nextInt(10), cars, request.getCount());
        }
        racingGameService.saveCarsState(gameId, cars);

        return racingGameService.getRacingGameResultResponse(cars);
    }

}
