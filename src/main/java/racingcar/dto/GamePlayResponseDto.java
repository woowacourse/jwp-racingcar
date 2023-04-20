package racingcar.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;
import racingcar.domain.Car;

public class GamePlayResponseDto {
    private final List<String> winners;
    private final List<CarDto> racingCars;

    public GamePlayResponseDto(final List<String> winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GamePlayResponseDto of(final List<String> winners, final List<Car> cars) {
        final List<CarDto> carDtos = cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(toList());
        return new GamePlayResponseDto(winners, carDtos);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
