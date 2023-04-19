package racingcar.dto;

import racingcar.entity.CarEntity;

import java.util.List;

public class GamePlayResponseDto {

    private final String winners;
    private final List<CarEntity> racingCars;

    public GamePlayResponseDto(final String winners, final List<CarEntity> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarEntity> getRacingCars() {
        return racingCars;
    }
}
