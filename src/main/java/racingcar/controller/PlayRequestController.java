package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlayRequestController {

    public static final String DELIMITER = ",";
    public static final int START_POSITION = 0;

    @PostMapping(path = "/plays")
    public ResponseEntity<PlayResponse> play(@RequestBody final PlayRequest request) {
        final List<String> names = Arrays.stream(request.getNames().split(DELIMITER)).collect(Collectors.toList());
        final int count = request.getCount();

        final RacingCars racingCars = generateRacingCars(names);
        race(count, racingCars);

        return ResponseEntity.ok().body(generateResponse(racingCars));
    }

    private RacingCars generateRacingCars(final List<String> names) {
        final List<Car> cars = names.stream()
                .map(name -> new Car(name, START_POSITION))
                .collect(Collectors.toList());
        return new RacingCars(cars);
    }

    private void race(final int count, final RacingCars racingCars) {
        final CarRandomNumberGenerator carRandomNumberGenerator = new CarRandomNumberGenerator();
        for (int i = 0; i < count; i++) {
            racingCars.tryOneTime(carRandomNumberGenerator);
        }
    }

    private PlayResponse generateResponse(final RacingCars racingCars) {
        final String winners = racingCars.getWinners().stream()
                .map(Car::getName)
                .collect(Collectors.joining(DELIMITER));
        return new PlayResponse(winners, racingCars.getCars());
    }
}
