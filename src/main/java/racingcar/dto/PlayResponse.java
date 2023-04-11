package racingcar.dto;

import java.util.List;

public class PlayResponse {

    private final List<String> winners;
    private final List<VehicleDto> racingCars;

    public PlayResponse(final List<String> winners, final List<VehicleDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<VehicleDto> getRacingCars() {
        return racingCars;
    }
}
