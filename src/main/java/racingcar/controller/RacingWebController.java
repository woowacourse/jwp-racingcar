package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.service.RacingService;

@RestController
public class RacingWebController {

    private final RacingService racingService;

    public RacingWebController(final RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public ResponseEntity<TrackResponse> play(@RequestBody @Valid final TrackRequest trackRequest) {
        final String names = trackRequest.getNames();
        final String trialTimes = trackRequest.getCount();

        final Cars finishedCars = racingService.play(names, trialTimes);

        return ResponseEntity.ok().body(makeResponse(finishedCars));
    }

    private TrackResponse makeResponse(final Cars finishedCars) {
        final String winnerCarNames = makeWinnerCarNames(finishedCars);
        final List<CarResponse> results = makeCarResponses(finishedCars);
        return new TrackResponse(winnerCarNames, results);
    }

    private String makeWinnerCarNames(final Cars finishedCars) {
        return finishedCars.getWinnerCars().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(", "));
    }

    private List<CarResponse> makeCarResponses(final Cars finishedCars) {
        return finishedCars.getCarsCurrentInfo().stream()
                .map(car -> new CarResponse(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
