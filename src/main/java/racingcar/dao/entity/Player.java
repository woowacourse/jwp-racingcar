package racingcar.dao.entity;

import java.util.Optional;

public class Player {

    private final Long playerId;
    private final String name;
    private final int position;
    private final boolean isWinner;
    private final Long gameId;

    public Player(String name, int position, boolean isWinner ,Long gameId) {
        this.playerId = null;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
        this.gameId = gameId;
    }

    public Optional<Long> getPlayerId() {
        return Optional.ofNullable(playerId);
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

    public Long getGameId() {
        return gameId;
    }
}
