package racingcar.dto;

import java.util.List;
import racingcar.domain.car.Car;

public class ResponseDTO {
    private final String winners;
    private final List<CarDTO> racingCars;

    public ResponseDTO(final String winners, final List<CarDTO> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDTO> getRacingCars() {
        return racingCars;
    }
}
