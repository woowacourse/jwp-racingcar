package racing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racing.util.RandomNumberGenerator;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.controller.dto.response.RacingGameResultResponse;
import racing.domain.Car;
import racing.domain.Cars;
import racing.service.RacingGameService;

import java.util.List;


@RestController
public class WebRacingGameController {

    private final RacingGameService racingGameService;

    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public RacingGameResultResponse start(@RequestBody RacingGameInfoRequest request) {
        Cars cars = racingGameService.createCars(request.getNames());
        Long gameId = racingGameService.createRacingGame(request.getCount());
        RandomNumberGenerator generator = new RandomNumberGenerator();

        moveCar(request, cars, generator);
        return racingGameService.saveCarsState(gameId, cars);
    }

    private void moveCar(RacingGameInfoRequest request, Cars cars, RandomNumberGenerator generator) {
        for (int i = 0; i < request.getCount(); i++) {
            for (Car car : cars.getCars()) {
                racingGameService.move(generator.generate(), car);
            }
        }
    }

    @GetMapping("/plays")
    public List<RacingGameResultResponse> getResult() {
        return racingGameService.getRacingGameResultResponse();
    }

}
