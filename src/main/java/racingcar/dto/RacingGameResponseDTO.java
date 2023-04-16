package racingcar.dto;

import java.util.List;

public class RacingGameResponseDTO {

    private String winners;
    private List<CarDTO> racingCars;

    public RacingGameResponseDTO() {
    }

    public RacingGameResponseDTO(final String winners, final List<CarDTO> racingCars) {
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
