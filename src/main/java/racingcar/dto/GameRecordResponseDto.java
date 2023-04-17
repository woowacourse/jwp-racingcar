package racingcar.dto;

import racingcar.dao.entity.CarEntity;

import java.util.List;

public class GameRecordResponseDto {
    private final String winners;
    private final List<CarEntity> racingCars;

    public GameRecordResponseDto(String winners, List<CarEntity> racingCars) {
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
