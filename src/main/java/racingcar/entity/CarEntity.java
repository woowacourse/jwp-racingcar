package racingcar.entity;

public final class CarEntity {

    private final String playerName;
    private final int finalPosition;
    private final boolean isWinner;
    private final Long gameResultId;

    public CarEntity(final String playerName, final int finalPosition,
                     final boolean isWinner, final Long gameResultId) {
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
