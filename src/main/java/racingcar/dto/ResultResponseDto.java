package racingcar.dto;

import java.util.List;
import racingcar.dao.entity.Player;

public class ResultResponseDto {

    private final String winners;
    private final List<Player> racingCars;

    public ResultResponseDto(String winners, List<Player> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Player> getRacingCars() {
        return racingCars;
    }
}
