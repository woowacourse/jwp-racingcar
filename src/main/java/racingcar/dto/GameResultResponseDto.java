package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Cars;

public class GameResultResponseDto {

    private final List<String> winners;
    private final List<CarStatusResponseDto> racingCars;

    private GameResultResponseDto(final List<String> winners, final List<CarStatusResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResultResponseDto toDto(final Cars cars) {
        List<CarStatusResponseDto> carStatuses = cars.getCars().stream()
                .map(CarStatusResponseDto::toDto)
                .collect(Collectors.toList());

        return new GameResultResponseDto(cars.getWinnerNames(), carStatuses);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarStatusResponseDto> getRacingCars() {
        return racingCars;
    }
}
