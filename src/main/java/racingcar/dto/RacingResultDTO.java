package racingcar.dto;

import java.util.List;
import java.util.Objects;

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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RacingResultDTO that = (RacingResultDTO) o;
        return Objects.equals(winners, that.winners) && Objects.equals(racingCars, that.racingCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winners, racingCars);
    }

    @Override
    public String toString() {
        return "RacingResultDTO{" +
                "winners='" + winners + '\'' +
                ", racingCars=" + racingCars +
                '}';
    }
}
