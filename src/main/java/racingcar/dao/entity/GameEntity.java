package racingcar.dao.entity;

public class GameEntity {

    private final Integer gameId;
    private final int trialCount;

    public GameEntity(final Integer gameId, final int trialCount) {
        this.gameId = gameId;
        this.trialCount = trialCount;
    }

    public int getGameId() {
        return gameId;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
