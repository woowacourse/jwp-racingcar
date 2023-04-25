package racingcar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import racingcar.domain.car.Car;
import racingcar.domain.car.Name;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@ToString
@Getter
public class GameOutputDto {
    private final String winners;
    private final List<CarResponseDto> racingCars;
    
    public GameOutputDto(final List<Car> winners, final List<Car> cars) {
        this.winners = parseWinners(winners);
        this.racingCars = parseCarResponseDtos(cars);
    }
    
    private String parseWinners(final List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .map(Name::getName)
                .collect(Collectors.joining(", "));
    }
    
    private List<CarResponseDto> parseCarResponseDtos(final List<Car> cars) {
        return cars.stream()
                .map(CarResponseDto::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
