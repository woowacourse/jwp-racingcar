package racingcar.dto;

import java.util.List;

public class ResponseDTO {

    private List<String> winners;
    private List<CarDTO> racingCars;

    public ResponseDTO() {
    }

    public ResponseDTO(final List<String> winners, final List<CarDTO> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDTO> getRacingCars() {
        return racingCars;
    }
}
