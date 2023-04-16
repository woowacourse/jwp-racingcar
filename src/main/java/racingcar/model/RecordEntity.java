package racingcar.model;

public class RecordEntity {
    private int gameId;
    private int position;
    private boolean isWinner;
    private String playerName;

    public RecordEntity() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(final int gameId) {
        this.gameId = gameId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(final int position) {
        this.position = position;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(final boolean isWinner) {
        this.isWinner = isWinner;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }
}
