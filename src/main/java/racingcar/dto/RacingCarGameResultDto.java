package racingcar.dto;

import java.util.List;

public class RacingCarGameResultDto {
    private List<String> winners;
    private List<RacingCarDto> racingCars;

    public RacingCarGameResultDto() {

    }

    public RacingCarGameResultDto(List<String> winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
