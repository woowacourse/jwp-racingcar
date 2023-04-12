package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.domain.*;
import racingcar.util.RandomNumberGenerator;
import racingcar.validation.Validation;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RacingCarController {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    @PostMapping("plays")
    public ResponseEntity<GameResultDto> createGame(@RequestBody GameInforamtionDto gameInforamtionDto) {
        String names = gameInforamtionDto.getNames();
        Validation.validateCarNames(names);
        Cars cars = new Cars(names);

        int count = gameInforamtionDto.getCount();
        Validation.validateTryCount(count);

        for (int number = 0; number < count; number++) {
            cars.moveForRound(randomNumberGenerator);
        }

        List<RacingCarDto> racingCars = new ArrayList<>();
        for (Car car : cars.getCars()) {
            RacingCarDto racingCarDto = new RacingCarDto(car.getName(), car.getLocation());
            racingCars.add(racingCarDto);
        }

        GameResultDto gameResultDto = new GameResultDto(cars.getWinnerCars(), racingCars);
        return ResponseEntity.ok().body(gameResultDto);
    }
}
