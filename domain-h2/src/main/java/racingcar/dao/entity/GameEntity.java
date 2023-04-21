package racingcar.dao.entity;

public class GameEntity {

    private final GameId gameId;
    private final int trialCount;

    public GameEntity(final Integer gameId, final int trialCount) {
        this.gameId = new GameId(gameId);
        this.trialCount = trialCount;
    }

    public GameId getGameId() {
        return gameId;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
