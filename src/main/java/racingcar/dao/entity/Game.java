package racingcar.dao.entity;

import java.util.Optional;
import racingcar.domain.RacingCarGame;

public class Game {

    private final Long gameId;
    private final Integer playCount;

    private Game(int playCount) {
        this.gameId = null;
        this.playCount = playCount;
    }

    public static Game of(RacingCarGame racingCarGame) {
        return new Game(racingCarGame.getCount());
    }

    public Optional<Long> getGameId() {
        return Optional.ofNullable(gameId);
    }

    public int getPlayCount() {
        return playCount;
    }

}
