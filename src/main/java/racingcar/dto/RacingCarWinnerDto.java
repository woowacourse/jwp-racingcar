package racingcar.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Name;

public class RacingCarWinnerDto {
    private final List<String> winners;

    public RacingCarWinnerDto(List<String> winners) {
        this.winners = winners;
    }

    public static RacingCarWinnerDto of(List<Car> winners) {
        List<String> names = winners.stream()
                .map(Car::getName)
                .map(Name::getName)
                .collect(toList());
        return new RacingCarWinnerDto(names);
    }

    public List<String> getWinners() {
        return winners;
    }

    @Override
    public String toString() {
        return String.join(", ", winners);
    }
}
