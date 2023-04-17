package racingcar.model;

public class RecordEntity {
    private final String playerName;
    private final int gameId;
    private final int position;
    private final boolean isWinner;


    public RecordEntity(final String playerName, final int gameId, final int position, final boolean isWinner) {
        this.playerName = playerName;
        this.gameId = gameId;
        this.position = position;
        this.isWinner = isWinner;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGameId() {
        return gameId;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
