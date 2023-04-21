package racingcar.dao.entity;

public class Winner {
    private final Long id;
    private final long gameId;
    private final String winner;

    public Winner(long gameId, String winner) {
        this.id = null;
        this.gameId = gameId;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public String getWinner() {
        return winner;
    }
}
