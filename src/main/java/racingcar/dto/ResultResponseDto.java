package racingcar.dto;

import java.util.List;

public class ResultResponseDto {

    private final String winners;
    private final List<PlayerDto> racingCars;

    public ResultResponseDto(String winners, List<PlayerDto> racingCars) {
        this.winners = winners;
        this.racingCars = List.copyOf(racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerDto> getRacingCars() {
        return racingCars;
    }
}
