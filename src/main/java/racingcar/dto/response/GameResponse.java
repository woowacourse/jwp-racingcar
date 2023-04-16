package racingcar.dto.response;


import racingcar.dto.CarDto;

import java.util.List;

public class GameResponse {
    private final String winners;
    private final List<CarDto> racingCars;

    public GameResponse(String winners, List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

    @Override
    public String toString() {
        return "GameResponseDto{" +
                "winners='" + winners + '\'' +
                ", racingCars=" + racingCars +
                '}';
    }
}
