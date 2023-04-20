package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.entity.Player;

public class PlayResponse {

    private final String winners;
    private final List<CarResponse> racingCars;

    private PlayResponse(String winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static PlayResponse of(String winners, List<Player> players) {
        List<CarResponse> carResponses = players.stream()
                .map(CarResponse::from)
                .collect(Collectors.toList());

        return new PlayResponse(winners, carResponses);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
