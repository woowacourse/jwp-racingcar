package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {
    private static final int START_POSITION = 0;

    private final CarNumberGenerator carNumberGenerator;

    public RacingCarService() {
        this.carNumberGenerator = new CarRandomNumberGenerator();
    }

    public RacingCarService(final CarNumberGenerator carNumberGenerator) {
        this.carNumberGenerator = carNumberGenerator;
    }

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
        for (int i = 0; i < trialCount; i++) {
            racingCars.tryOneTime(carNumberGenerator);
        }
    }

    private static List<String> getWinnerNames(final RacingCars racingCars) {
        return racingCars.getWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
