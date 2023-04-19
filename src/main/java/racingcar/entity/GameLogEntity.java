package racingcar.entity;

public class GameLogEntity {
    private final String playerName;
    private final int resultPosition;

    public GameLogEntity(String playerName, int resultPosition) {
        this.playerName = playerName;
        this.resultPosition = resultPosition;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getResultPosition() {
        return resultPosition;
    }
}
