package racingcar;

import java.util.List;
import racingcar.dto.RacingCarResultDto;
import racingcar.dto.RacingCarWinnerResponse;

public class GameResultDto {
    private final String winners;
    private final List<RacingCarResultDto> racingCars;

    private GameResultDto(List<String> winners, List<RacingCarResultDto> racingCars) {
        this.winners = String.join(",", winners);
        this.racingCars = racingCars;
    }

    public static GameResultDto of(RacingCarWinnerResponse winners, List<RacingCarResultDto> racingCars) {
        return new GameResultDto(winners.getWinners(), racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarResultDto> getRacingCars() {
        return racingCars;
    }
}
