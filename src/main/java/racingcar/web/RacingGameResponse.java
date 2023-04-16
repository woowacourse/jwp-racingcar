package racingcar.web;

import java.util.List;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;

public class RacingGameResponse {
    private String winners;
    private List<CarDto> racingCars;

    public RacingGameResponse(String winners, List<CarDto> racingCars) {
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

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
