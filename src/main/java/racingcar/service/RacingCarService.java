package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.PlaysRequestDto;
import racingcar.controller.dto.PlaysResponseDto;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameCount;
import racingcar.domain.PowerGenerator;
import racingcar.util.CarNamesDivider;

import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class RacingCarService {

    public PlaysResponseDto plays(PlaysRequestDto playsRequestDto) {
        Cars cars = generateCars(playsRequestDto);
        GameCount gameCount = new GameCount(playsRequestDto.getCount());
        progress(cars, gameCount);
        String winners = winnersToString(cars);
        return new PlaysResponseDto(winners, cars.getCars());
    }

    private static Cars generateCars(PlaysRequestDto playsRequestDto) {
        CarNamesDivider carNamesDivider = new CarNamesDivider();
        List<String> carNamesByDivider = carNamesDivider.divideCarNames(playsRequestDto.getNames());
        List<Car> inputCars = carNamesByDivider.stream()
                .map(Car::new)
                .collect(toList());
        return new Cars(inputCars);
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

    private String winnersToString(Cars cars) {
        return cars.getWinners().stream()
                .map(Car::getName)
                .collect(joining(","));
    }

}
