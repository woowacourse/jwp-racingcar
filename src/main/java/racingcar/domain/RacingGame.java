package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import racingcar.dto.JudgedCarDto;

@Component
public class RacingGame {

    private static final int POWER_VALUE_MIN = 0;
    private static final int POWER_VALUE_MAX = 9;

    private final NumberGenerator powerValueGenerator = new RandomNumberGenerator(POWER_VALUE_MIN, POWER_VALUE_MAX);
    private RacingCars racingCars;

    public List<JudgedCarDto> play(int racingCount, List<String> carNames) {
        racingCars = new RacingCars(carNames);
        race(racingCount);
        return getRacedCars();
    }

    private void race(int count) {
        for (int i = 0; i < count; i++) {
            racingCars.process(powerValueGenerator);
        }
    }

    private List<JudgedCarDto> getRacedCars() {
        List<Car> winningCars = racingCars.findWinningCars();
        return racingCars.racingCars()
                .stream()
                .map(car -> JudgedCarDto.of(car.getName(), car.getPosition(), winningCars.contains(car)))
                .collect(Collectors.toList());
    }
}
