package racingcar.dto;

import java.util.List;

public class RacingCarGameResultDto {

    private final List<String> winners;
    private final List<RacingCarDto> racingCars;

    public RacingCarGameResultDto(List<String> winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }
}
