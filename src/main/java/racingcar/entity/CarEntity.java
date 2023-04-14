package racingcar.entity;

public final class CarEntity {

    private final String playerName;
    private final int finalPosition;
    private final boolean isWinner;
    private final Long gameResultId;

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

}
