package racingcar.dao.entity;

public class WinnerEntity {

    private final int gameId;

    public WinnerEntity(final int gameId, final int winnerCarId, final Integer winnerId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }
}
