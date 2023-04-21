package racingcar.entity;

public class RacingCarEntity {
    private final String name;
    private final int position;
    private final boolean isWin;
    private final long gameId;

    public RacingCarEntity(final String name, final int position, final boolean isWin, final long gameId) {
        this.name = name;
        this.position = position;
        this.isWin = isWin;
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean getIsWin() {
        return isWin;
    }

    public long getGameId() {
        return gameId;
    }
}

