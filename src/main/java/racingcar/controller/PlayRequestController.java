package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.database.RacingCarDao;
import racingcar.database.RacingGameDao;
import racingcar.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlayRequestController {

    private static final String DELIMITER = ",";
    private static final int START_POSITION = 0;

    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;

    @Autowired
    public PlayRequestController(final RacingGameDao racingGameDao, final RacingCarDao racingCarDao) {
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
    }

    @PostMapping(path = "/plays")
    public ResponseEntity<PlayResponse> play(@RequestBody final PlayRequest request) {
        final List<String> names = Arrays.stream(request.getNames().split(DELIMITER)).collect(Collectors.toList());
        final int count = request.getCount();

        final RacingCars racingCars = generateRacingCars(names);
        race(count, racingCars);

        final String winners = generateWinners(racingCars);
        racingGameDao.insert(count, winners);
        final int gameId = racingGameDao.getGameId();
        racingCars.getCars().forEach(car -> racingCarDao.insert(car, gameId));

        return ResponseEntity.ok().body(generateResponse(winners, racingCars));
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

    private static String generateWinners(final RacingCars racingCars) {
        return racingCars.getWinners().stream()
                .map(Car::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private PlayResponse generateResponse(final String winners, final RacingCars racingCars) {
        return new PlayResponse(winners, racingCars.getCars());
    }
}
