package racingcar.dto;

import java.util.List;

public class ResponseDTO {
    private String winners;
    private List<CarDTO> racingCars;

    public ResponseDTO() {
    }

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
