package racingcar.dto.response;

import java.util.List;

public class GameResponseDto {
    private final String winners;
    private final List<PlayerResultDto> racingCars;

    private GameResponseDto(final String winners, final List<PlayerResultDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponseDto of(final List<String> winners, final List<PlayerResultDto> cars) {
        return new GameResponseDto(String.join(",", winners), cars);
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResultDto> getRacingCars() {
        return racingCars;
    }
}
