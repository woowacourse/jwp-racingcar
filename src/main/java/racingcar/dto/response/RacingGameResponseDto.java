package racingcar.dto.response;

import java.util.List;

public class RacingGameResponseDto {

    private String winners;
    private List<RacingCarResponseDto> racingCars;

    public RacingGameResponseDto(final String winners, final List<RacingCarResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarResponseDto> getRacingCars() {
        return racingCars;
    }
}
