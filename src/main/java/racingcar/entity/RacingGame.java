package racingcar.entity;

import java.util.List;

public class RacingGame {

    private final Game game;
    private final List<Player> player;

    public RacingGame(final Game game, final List<Player> player) {
        this.game = game;
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public List<Player> getPlayer() {
        return player;
    }
}
