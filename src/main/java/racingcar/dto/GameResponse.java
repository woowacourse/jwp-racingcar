package racingcar.dto;

import java.util.List;

public class GameResponse {
    private final String winners;
    private final List<CarDto> cars;

    public GameResponse(final String winners, final List<CarDto> cars) {
        this.winners = winners;
        this.cars = cars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getCars() {
        return cars;
    }
}
