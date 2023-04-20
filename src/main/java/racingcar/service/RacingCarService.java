package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.model.Car;
import racingcar.model.CarRandomNumberGenerator;
import racingcar.model.RacingCarResult;
import racingcar.model.RacingCars;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {
    private static final int START_POSITION = 0;

    public RacingCarResult playRacingCar(final List<String> names, final int trialCount) {
        final RacingCars racingCars = generateRacingCars(names);

        race(racingCars, trialCount);

        return new RacingCarResult(getWinnerNames(racingCars), racingCars.getCars());
    }

    private RacingCars generateRacingCars(final List<String> names) {
        final List<Car> cars = names.stream()
                .map(name -> new Car(name, START_POSITION))
                .collect(Collectors.toList());
        return new RacingCars(cars);
    }

    private void race(final RacingCars racingCars, final int trialCount) {
        final CarRandomNumberGenerator carRandomNumberGenerator = new CarRandomNumberGenerator();
        for (int i = 0; i < trialCount; i++) {
            racingCars.tryOneTime(carRandomNumberGenerator);
        }
    }

    private static List<String> getWinnerNames(final RacingCars racingCars) {
        return racingCars.getWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
