package racingcar.entity;

public class GameHistoryEntity {

    private final long id;
    private final String winners;
    private final String playerName;
    private final int playerPosition;

    public GameHistoryEntity(final long id, final String winners, final String playerName, final int playerPosition) {
        this.id = id;
        this.winners = winners;
        this.playerName = playerName;
        this.playerPosition = playerPosition;
    }

    public long getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }
}
