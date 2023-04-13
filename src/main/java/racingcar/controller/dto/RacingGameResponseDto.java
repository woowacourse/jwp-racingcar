package racingcar.controller.dto;

import java.util.List;

public class RacingGameResponseDto {

    private final int id;
    private final String winners;
    private final List<RacingCarResponseDto> racingCars;

    public RacingGameResponseDto(int id, String winners, List<RacingCarResponseDto> racingCars) {
        this.id = id;
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarResponseDto> getRacingCars() {
        return racingCars;
    }

}
