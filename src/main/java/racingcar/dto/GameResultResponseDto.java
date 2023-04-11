package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Cars;

public class GameResultResponseDto {

    private String winners;
    private List<CarStatusResponseDto> racingCars;

    private GameResultResponseDto(final String winners, final List<CarStatusResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResultResponseDto toDto(final String winners, final Cars cars) {
        List<CarStatusResponseDto> carStatuses = cars.getCars().stream()
                .map(CarStatusResponseDto::toDto)
                .collect(Collectors.toList());

        return new GameResultResponseDto(winners, carStatuses);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarStatusResponseDto> getRacingCars() {
        return racingCars;
    }
}
