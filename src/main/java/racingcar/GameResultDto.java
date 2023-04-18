package racingcar;

import java.util.List;
import racingcar.dto.ResultDto;
import racingcar.dto.WinnerDto;

public class GameResultDto {
    private final String winners;
    private final List<ResultDto> racingCars;

    public GameResultDto(List<String> winners, List<ResultDto> racingCars) {
        this.winners = String.join(",", winners);
        this.racingCars = racingCars;
    }

    public static GameResultDto of(WinnerDto winners, List<ResultDto> racingCars) {
        return new GameResultDto(winners.getWinners(), racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<ResultDto> getRacingCars() {
        return racingCars;
    }
}
