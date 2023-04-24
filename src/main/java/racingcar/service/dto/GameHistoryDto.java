package racingcar.service.dto;

import java.util.List;

public class GameHistoryDto {

    private final List<String> winners;
    private final List<RacingCarDto> racingCars;

    public GameHistoryDto(final List<String> winners, final List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }

    @Override
    public String toString() {
        return "GameHistoryDto{" +
                "winners='" + winners + '\'' +
                ", racingCars=" + racingCars +
                '}';
    }
}
