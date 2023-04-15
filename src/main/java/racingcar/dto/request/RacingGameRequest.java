package racingcar.dto.request;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;

public class RacingGameRequest {

    private final List<String> names;
    private final int count;

    public RacingGameRequest(String names, int count) {
        this.names = Arrays.asList(names.trim().split(","));
        this.count = count;
    }

    public RacingGame toEntity() {
        final Cars cars = names.stream()
                .map(Car::new)
                .collect(collectingAndThen(toList(), Cars::new));
        return new RacingGame(cars, count);
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
