package racingcar.dto;

import racingcar.domain.Car;
import racingcar.domain.Winner;

import java.util.List;
import java.util.Locale;

public class PlayResultResponseDto {
    private final Winner winner;
    private final List<Car> racingCars;

    public PlayResultResponseDto(Winner winner, List<Car> racingCars) {
        this.winner = winner;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return new WinnerFormatter().print(winner, Locale.getDefault());
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
