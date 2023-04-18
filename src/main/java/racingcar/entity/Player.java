package racingcar.entity;

import racingcar.domain.Car;

public class Player {
    private final Integer id;
    private final String name;
    private final int position;
    private final boolean isWinner;
    private final int game_id;

    public Player(final Integer id, final String name, final int position, final boolean isWinner, final int game_id) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
        this.game_id = game_id;
    }

    public Player (final Car player, final boolean isWinner, final int game_id) {
        this(null, player.getName(), player.getPosition(), isWinner, game_id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public int getGame_id() {
        return game_id;
    }
}
