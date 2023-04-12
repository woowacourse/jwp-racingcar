package racingcar.controller;

import org.springframework.web.bind.annotation.*;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameInforamtionDto;
import racingcar.util.RandomNumberGenerator;
import racingcar.validation.Validation;

import java.util.List;

@RestController
public class RacingCarController {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    @PostMapping("plays")
    public void createGame(@RequestBody GameInforamtionDto gameInforamtionDto) {
        String names = gameInforamtionDto.getNames();
        Validation.validateCarNames(names);
        Cars cars = new Cars(names);

        int count = gameInforamtionDto.getCount();
        Validation.validateTryCount(count);

        for (int number = 0; number < count; number++) {
            cars.moveForRound(randomNumberGenerator);
        }
    }
}
