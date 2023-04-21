package racingcar.controller.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.RacingCar;

public class GameResponse {

    private final String winners;
    private final List<RacingCarDto> racingCars;

    public GameResponse(final String winners, final List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public GameResponse(final List<String> winnerNames, final List<RacingCar> racingCars) {
        this(String.join(", ", winnerNames), mapToRacingCarDto(racingCars));
    }

    private static List<RacingCarDto> mapToRacingCarDto(final List<RacingCar> racingCars) {
        return racingCars.stream().map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                .collect(
                        Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
