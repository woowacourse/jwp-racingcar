package racingcar.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class RacingGameResponse {

    private String winners;
    private List<RacingCarResponse> racingCars;

    private RacingGameResponse(final String winners, final List<RacingCarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingGameResponse of(final List<String> winners, final List<Car> currentResult) {
        return new RacingGameResponse(String.join(",", winners), generateRacingCars(currentResult));
    }

    private static List<RacingCarResponse> generateRacingCars(final List<Car> currentResult) {
        return currentResult.stream()
                .map(RacingCarResponse::from)
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarResponse> getRacingCars() {
        return racingCars;
    }
}
