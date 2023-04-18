package racingcar.dto;

import java.util.List;

public class RacingCarGameResultDto {
    private String winners;
    private List<RacingCarDto> racingCars;

    public RacingCarGameResultDto() {

    }

    public RacingCarGameResultDto(String winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
