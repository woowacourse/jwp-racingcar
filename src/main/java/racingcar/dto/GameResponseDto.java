package racingcar.dto;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;

import java.util.ArrayList;
import java.util.List;

public class GameResponseDto {
    private final String winners;
    private final List<PlayerResponse> racingCars;

    private GameResponseDto(final String winners, final List<PlayerResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponseDto of(final Game game, final List<PlayerResult> cars) {
        final List<PlayerResponse> racingCars = new ArrayList<>();
        for (PlayerResult playerResult : cars) {
            racingCars.add(new PlayerResponse(playerResult.getName(), playerResult.getFinalPosition()));
        }
        return new GameResponseDto(String.join(",", game.getWinners()), racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResponse> getRacingCars() {
        return racingCars;
    }

    private static class PlayerResponse {
        private final String name;
        private final int position;

        public PlayerResponse(final String name, final int position) {
            this.name = name;
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }
    }
}
