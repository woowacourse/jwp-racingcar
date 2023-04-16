package racingcar.dto;

import racingcar.Entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class GameResponse {
    private final String winners;
    private final List<PlayerDto> racingCars;

    public GameResponse(final String winners, final List<PlayerDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponse of(final String winners, final List<Player> players) {
        final List<PlayerDto> playerDtos = players.stream()
                .map(car -> new PlayerDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());

        return new GameResponse(winners, playerDtos);
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerDto> getRacingCars() {
        return racingCars;
    }
}
