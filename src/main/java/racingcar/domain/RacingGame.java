package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import racingcar.dto.CarDto;

@Component
public class RacingGame {

    private static final int POWER_VALUE_MIN = 0;
    private static final int POWER_VALUE_MAX = 9;

    private final NumberGenerator powerValueGenerator = new RandomNumberGenerator(POWER_VALUE_MIN, POWER_VALUE_MAX);
    private RacingCars racingCars;

    public List<CarDto> play(int trialCount, List<String> carNames) {
        racingCars = new RacingCars(carNames);
        race(trialCount);
        return getRacedCars();
    }

    private void race(int count) {
        for (int i = 0; i < count; i++) {
            racingCars.process(powerValueGenerator);
        }
    }

    private List<CarDto> getRacedCars() {
        List<Car> winningCars = racingCars.findWinningCars();
        return racingCars.racingCars()
                .stream()
                .map(car -> CarDto.of(car.getName(), car.getPosition(), winningCars.contains(car)))
                .collect(Collectors.toList());
    }
}
