package racingcar.dto;

import java.util.List;

import racingcar.entity.CarEntity;

public class RacingCarResponseDto {
    private final String winners;
    private final List<CarEntity> racingCars;

    public RacingCarResponseDto(final String winners, final List<CarEntity> racingCars) {
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
