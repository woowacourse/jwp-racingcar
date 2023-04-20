package racingcar.dto;

import java.util.List;

public class RacingResultDTO {
    private String winners;
    private List<CarDTO> racingCars;

    public RacingResultDTO() {
    }

    public RacingResultDTO(final String winners, final List<CarDTO> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDTO> getRacingCars() {
        return racingCars;
    }

    @Override
    public String toString() {
        return "RacingResultDTO{" +
                "winners='" + winners + '\'' +
                ", racingCars=" + racingCars +
                '}';
    }
}
