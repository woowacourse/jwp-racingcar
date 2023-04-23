package racingcar.controller.dto;

import java.util.List;

public class GameResponseDto {

    private final int id;
    private final String winners;
    private final List<CarResponseDto> racingCars;

    public GameResponseDto(int id, String winners, List<CarResponseDto> racingCars) {
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

    public List<CarResponseDto> getRacingCars() {
        return racingCars;
    }

    @Override
    public String toString() {
        return "GameResponseDto{" +
                "id=" + id +
                ", winners='" + winners + '\'' +
                ", racingCars=" + racingCars +
                '}';
    }

}
