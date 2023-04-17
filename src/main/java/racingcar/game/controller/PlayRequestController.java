package racingcar.game.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.car.model.Car;
import racingcar.car.model.CarRandomNumberGenerator;
import racingcar.car.repository.RacingCarDao;
import racingcar.game.model.PlayRequest;
import racingcar.game.model.PlayResponse;
import racingcar.game.model.RacingCars;
import racingcar.game.repository.RacingGameDao;

@RestController
public class PlayRequestController {
    
    private static final String DELIMITER = ",";
    private static final int START_POSITION = 0;
    
    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;
    
    public PlayRequestController(final RacingGameDao racingGameDao, final RacingCarDao racingCarDao) {
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
    }
    
    @PostMapping(path = "/plays")
    public ResponseEntity<PlayResponse> play(@RequestBody final PlayRequest request) {
        final List<String> names = Arrays.stream(request.getNames().split(DELIMITER)).collect(Collectors.toList());
        final int count = request.getCount();
        
        final RacingCars racingCars = this.generateRacingCars(names);
        this.race(count, racingCars);
        
        final String winners = generateWinners(racingCars);
        
        final int gameId = this.racingGameDao.insert(count, winners);
        
        racingCars.getCars().forEach(car -> this.racingCarDao.insert(car, gameId));
        
        return ResponseEntity.ok().body(this.generateResponse(winners, racingCars));
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
