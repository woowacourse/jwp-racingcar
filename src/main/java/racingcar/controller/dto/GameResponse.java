package racingcar.controller.dto;

import racingcar.service.dto.GameHistoryDto;
import racingcar.service.dto.RacingCarDto;

import java.util.List;

public class GameResponse {

    private final String winners;
    private final List<RacingCarDto> racingCars;

    public GameResponse(final String winners, final List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponse from(GameHistoryDto gameHistoryDto) {
        final String winners = String.join(", ", gameHistoryDto.getWinners());
        final List<RacingCarDto> racingCars = gameHistoryDto.getRacingCars();

        return new GameResponse(winners, racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }

    @Override
    public String toString() {
        return "GameResponse{" +
                "winners='" + winners + '\'' +
                ", racingCars=" + racingCars +
                '}';
    }
}
