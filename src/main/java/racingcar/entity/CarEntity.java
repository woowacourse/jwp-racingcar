package racingcar.entity;

public class CarEntity {

    private final String playerName;
    private final int finalPosition;
    private final Long gameResultId;

    public CarEntity(String playerName, int finalPosition, Long gameResultId) {
        this.playerName = playerName;
        this.finalPosition = finalPosition;
        this.gameResultId = gameResultId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public Long getGameResultId() {
        return gameResultId;
    }
}
