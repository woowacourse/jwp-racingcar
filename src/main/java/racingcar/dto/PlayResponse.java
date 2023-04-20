package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.entity.Player;

public class PlayResponse {

    private final List<String> winners;
    private final List<CarResponse> racingCars;

    private PlayResponse(List<String> winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static PlayResponse from(List<Player> players) {
        List<String> winners = players.stream()
                .filter(Player::isWinner)
                .map(Player::getName)
                .collect(Collectors.toList());

        List<CarResponse> racingCars = players.stream()
                .map(CarResponse::from)
                .collect(Collectors.toList());

        return new PlayResponse(winners, racingCars);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
