package racingcar.entity;

import java.util.List;

public class RacingGameEntity {

    private final Game game;
    private final List<Player> player;

    public RacingGameEntity(final Game game, final List<Player> player) {
        this.game = game;
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public List<Player> getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "RacingGameEntity{" +
                "game=" + game +
                ", player=" + player +
                '}';
    }
}
