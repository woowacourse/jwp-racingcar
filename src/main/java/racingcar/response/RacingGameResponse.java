package racingcar.response;

import java.util.List;
import racingcar.dto.CarDTO;

public class RacingGameResponse {

    private String winners;
    private List<CarDTO> racingCars;

    public RacingGameResponse() {
    }

    public RacingGameResponse(final String winners, final List<CarDTO> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDTO> getRacingCarDTOs() {
        return racingCars;
    }
}
