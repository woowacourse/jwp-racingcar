package racingcar.dto;

import lombok.Getter;
import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.car.Name;
import racingcar.domain.racinggame.RacingGame;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GameResponseDto {
    private final String winners;
    private final List<CarResponseDto> racingCars;
    
    public GameResponseDto(final RacingGame racingGame) {
        final Cars cars = racingGame.getCars();
        this.winners = parseWinners(cars);
        this.racingCars = parseCarResponseDtos(cars);
    }
    
    private List<CarResponseDto> parseCarResponseDtos(final Cars cars) {
        return cars.getCars().stream()
                .map(CarResponseDto::new)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private String parseWinners(final Cars cars) {
        return cars.getWinners().stream()
                .map(Car::getName)
                .map(Name::getName)
                .collect(Collectors.joining(", "));
    }
}
