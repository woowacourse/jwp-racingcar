package racingcar.dto;


import java.util.List;

public class GameResultResponseDto {
    private final String winners;
    private final List<CarDto> racingCars;

    public GameResultResponseDto(String winners, List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
