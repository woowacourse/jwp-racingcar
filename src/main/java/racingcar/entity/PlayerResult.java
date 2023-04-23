package racingcar.entity;

public class PlayerResult {

    private long id;
    private final String name;
    private final int finalPosition;
    private final long gameId;

    public PlayerResult(final long id, final String name, final int finalPosition, final long gameId) {
        this.id = id;
        this.name = name;
        this.finalPosition = finalPosition;
        this.gameId = gameId;
    }

    public PlayerResult(final String name, final int finalPosition, final long gameId) {
        this.name = name;
        this.finalPosition = finalPosition;
        this.gameId = gameId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public long getGameId() {
        return gameId;
    }
}
