package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;
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

    public List<String> getWinners() {
        return winners;
    }

    public List<PlayerDto> getRacingCars() {
        return racingCars;
    }
}
