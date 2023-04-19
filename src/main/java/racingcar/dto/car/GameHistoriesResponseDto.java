package racingcar.dto.car;

import java.util.List;

public class GameHistoriesResponseDto {

    private final String winners;
    private final List<CarStatusResponseDto> racingCars;

    private GameHistoriesResponseDto(final String winners, final List<CarStatusResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameHistoriesResponseDto toDto(final String winners, List<CarStatusResponseDto> racingCars) {
        return new GameHistoriesResponseDto(winners, racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarStatusResponseDto> getRacingCars() {
        return racingCars;
    }
}
