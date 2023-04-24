package racingcar.controller.web;

import java.util.List;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingGameDto;

public class RacingGameResponse {
    private final String winners;
    private final List<RacingCarDto> racingCars;

    public RacingGameResponse(String winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingGameResponse from(RacingGameDto racingGameDto) {
        return new RacingGameResponse(joinNames(racingGameDto.getWinnerNames()), racingGameDto.getRacingCars());
    }

    private static String joinNames(List<String> names) {
        return String.join(",", names);
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
