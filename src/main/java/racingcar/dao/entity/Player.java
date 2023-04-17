package racingcar.dao.entity;

public class Player {

    private final Long playerId;
    private final String name;
    private final int position;
    private final Long gameId;

    public Player(String name, int position, Long gameId) {
        this.playerId = null;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public Player(Long playerId, String name, int position, Long gameId) {
        this.playerId = playerId;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Long getGameId() {
        return gameId;
    }
}
