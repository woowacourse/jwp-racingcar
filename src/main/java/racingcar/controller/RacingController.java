package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.PlaysRequestDto;
import racingcar.controller.dto.PlaysResponseDto;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameCount;
import racingcar.domain.PowerGenerator;
import racingcar.util.CarNamesDivider;

import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.*;

@RestController
public class RacingController {

    private final CarNamesDivider carNamesDivider;

    public RacingController() {
        carNamesDivider = new CarNamesDivider();
    }

    @PostMapping("/plays")
    public ResponseEntity<PlaysResponseDto> run(@RequestBody PlaysRequestDto playsRequestDto) {
        List<String> carNamesByDivider = carNamesDivider.divideCarNames(playsRequestDto.getNames());
        List<Car> inputCars = carNamesByDivider.stream()
                .map(Car::new)
                .collect(toList());
        Cars cars = new Cars(inputCars);
        GameCount gameCount = new GameCount(playsRequestDto.getCount());
        progress(cars, gameCount);
        String winners = cars.getWinners().stream()
                .map(Car::getName)
                .collect(joining(","));
        return ResponseEntity.ok().body(new PlaysResponseDto(winners, cars.getCars()));
    }

    private void progress(Cars cars, GameCount gameCount) {
        while (gameCount.isGameProgress()) {
            gameCount.proceedOnce();
            moveAllCar(cars);
        }
    }

    private void moveAllCar(Cars cars) {
        cars.moveAll(new PowerGenerator(new Random()));
    }

}