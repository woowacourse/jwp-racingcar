package racingcar.response;

import racingcar.dto.VehicleDto;
import java.util.List;

public class PlayResponse {

    private final String winners;
    private final List<VehicleDto> racingCars;

    public PlayResponse(final List<String> winners, final List<VehicleDto> racingCars) {
        this.winners = convertToString(winners);
        this.racingCars = racingCars;
    }

    private String convertToString(final List<String> winners) {
        return String.join(",", winners);
    }

    public String getWinners() {
        return winners;
    }

    public List<VehicleDto> getRacingCars() {
        return racingCars;
    }
}
