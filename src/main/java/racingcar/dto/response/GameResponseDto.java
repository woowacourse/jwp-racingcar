package racingcar.dto.response;


import racingcar.dto.PlayerResultDto;

import java.util.List;

public class GameResponseDto {
    private final String winners;
    private final List<PlayerResultDto> racingCars;

    public GameResponseDto(String winners, List<PlayerResultDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResultDto> getRacingCars() {
        return racingCars;
    }
}
