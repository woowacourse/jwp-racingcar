package racingcar.dto;

import racingcar.domain.Car;
import racingcar.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResponse {
    private final List<String> winners;
    private final List<PlayerDto> racingCars;

    public GameResponse(final List<String> winners, final List<PlayerDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponse of(final List<String> winners, final List<Car> players) {
        final List<PlayerDto> playerDtos = players.stream()
                .map(car -> new PlayerDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());

        return new GameResponse(winners, playerDtos);
    }

    public static List<GameResponse> toGamePlayResponse(final List<Player> players) {
        final Map<Integer, List<Player>> gameIdByPlayers = players.stream()
                .collect(Collectors.groupingBy(Player::getGameId));

        return gameIdByPlayers.values().stream()
                .map(GameResponse::mapToGamePlayResponse)
                .collect(Collectors.toList());
    }

    private static GameResponse mapToGamePlayResponse(final List<Player> value) {
        List<String> winners = value.stream()
                .filter(Player::isWinner)
                .map(Player::getName)
                .collect(Collectors.toList());

        List<PlayerDto> playerDtos = value.stream()
                .map(player -> new PlayerDto(player.getName(), player.getPosition()))
                .collect(Collectors.toList());
        return new GameResponse(winners, playerDtos);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<PlayerDto> getRacingCars() {
        return racingCars;
    }
}
