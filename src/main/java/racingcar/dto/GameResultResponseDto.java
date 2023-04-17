package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public final class GameResultResponseDto {

    private final List<String> winners;
    private final List<CarDto> racingCars;

    public GameResultResponseDto(final List<Car> cars) {
        this.winners = cars.stream()
                .filter(Car::isWinner)
                .map(Car::getNameValue)
                .collect(Collectors.toList());
        this.racingCars = cars.stream()
                .map(carEntity -> new CarDto(carEntity.getNameValue(), carEntity.getPositionValue()))
                .collect(Collectors.toList());
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
