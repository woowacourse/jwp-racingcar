package racingcar.dto;

import racingcar.entity.Game;
import racingcar.entity.Player;

import java.util.List;

public class RacingGameFindDto {

    private final Game game;
    private final List<Player> player;

    public RacingGameFindDto(final Game game, final List<Player> player) {
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
