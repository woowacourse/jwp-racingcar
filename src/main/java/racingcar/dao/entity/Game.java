package racingcar.dao.entity;

import java.util.Optional;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.RacingCarGame;

public class Game {

    private final Long gameId;
    private final Integer playCount;
    private final String winners;

    private Game(int playCount, String winners) {
        this.gameId = null;
        this.playCount = playCount;
        this.winners = winners;
    }

    public static Game of(RacingCarGame racingCarGame) {
        String winners = racingCarGame.findWinners().getAll().stream()
            .map(Car::getName)
            .collect(Collectors.joining(","));
        return new Game(racingCarGame.getCount(), winners);
    }

    public Optional<Long> getGameId() {
        return Optional.ofNullable(gameId);
    }

    public int getPlayCount() {
        return playCount;
    }

    public String getWinners() {
        return winners;
    }

}
