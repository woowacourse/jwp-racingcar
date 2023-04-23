package racingcar.service.dto;

import java.util.List;

public class RacingCarGameResultDto {

    private final List<String> winners;
    private final List<RacingCarDto> racingCars;

    public RacingCarGameResultDto(final List<String> winners, final List<RacingCarDto> racingCars) {
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
