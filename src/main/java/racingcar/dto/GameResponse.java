package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public class GameResponse {
    private static final String DELIMITER = ",";

    private final String winners;
    private final List<CarDto> racingCars;

    private GameResponse(final String winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponse from(final ResultDto resultDto) {
        List<CarDto> winners = resultDto.getWinners();
        String winnerNames = winners.stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(DELIMITER));

        return new GameResponse(winnerNames, resultDto.getCars());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
