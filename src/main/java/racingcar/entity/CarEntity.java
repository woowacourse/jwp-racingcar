package racingcar.entity;

public class CarEntity {
    private String playerName;
    private int finalPosition;
    private boolean isWinner;
    private Long gameResultId;

    public CarEntity(String playerName, int finalPosition, boolean isWinner, Long gameResultId) {
        this.playerName = playerName;
        this.finalPosition = finalPosition;
        this.isWinner = isWinner;
        this.gameResultId = gameResultId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public Long getGameResultId() {
        return gameResultId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setFinalPosition(int finalPosition) {
        this.finalPosition = finalPosition;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public void setGameResultId(Long gameResultId) {
        this.gameResultId = gameResultId;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "playerName='" + playerName + '\'' +
                ", finalPosition=" + finalPosition +
                ", isWinner=" + isWinner +
                ", gameResultId=" + gameResultId +
                '}';
    }
}
