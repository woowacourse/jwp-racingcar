package racingcar.dto.response;

import java.util.List;

public class GameResultDto {
    private final String winners;
    private final List<PlayerResultDto> racingCars;

    public GameResultDto(String winners, List<PlayerResultDto> playerResults) {
        this.winners = winners;
        this.racingCars = playerResults;
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResultDto> getRacingCars() {
        return racingCars;
    }
}
