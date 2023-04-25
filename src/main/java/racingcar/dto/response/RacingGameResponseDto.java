package racingcar.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class RacingGameResponseDto {

    private String winners;
    private List<RacingCarResponseDto> racingCars;

    public RacingGameResponseDto(final String winners, final List<RacingCarResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingGameResponseDto of(final List<String> winners, final List<Car> currentResult) {
        return new RacingGameResponseDto(String.join(",", winners), generateRacingCars(currentResult));
    }

    private static List<RacingCarResponseDto> generateRacingCars(final List<Car> currentResult) {
        return currentResult.stream()
                .map(RacingCarResponseDto::from)
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarResponseDto> getRacingCars() {
        return racingCars;
    }
}
