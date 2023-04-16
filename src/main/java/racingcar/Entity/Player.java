package racingcar.Entity;

import racingcar.domain.Car;

public class Player {
    private final Integer id;
    private final String name;
    private final int position;
    private final int game_id;

    private Player(final Integer id, final String name, final int position, final int game_id) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.game_id = game_id;
    }

    public static Player of(final Car player, final int game_id) {
        final String playerName = player.getName();
        final int playerPosition = player.getPosition();
        return new Player(null, playerName, playerPosition, game_id);
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

    public int getGame_id() {
        return game_id;
    }
}
