package racingcar.dto;

public class PlayerResultSaveDto {
    private final long gameId;
    private final String name;
    private final int finalPosition;

    public PlayerResultSaveDto(final long gameId, final String name, final int finalPosition) {
        this.gameId = gameId;
        this.name = name;
        this.finalPosition = finalPosition;
    }

    public long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getFinalPosition() {
        return finalPosition;
    }
}
