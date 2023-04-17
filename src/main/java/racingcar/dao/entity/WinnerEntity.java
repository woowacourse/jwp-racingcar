package racingcar.dao.entity;

public class WinnerEntity {

    private final int gameId;
    private final int winnerCarId;

    public WinnerEntity(final int gameId, final int winnerCarId, final Integer winnerId) {
        this.gameId = gameId;
        this.winnerCarId = winnerCarId;
    }

    public int getGameId() {
        return gameId;
    }

    public int getWinnerCarId() {
        return winnerCarId;
    }
}
