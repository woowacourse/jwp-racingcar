package racingcar.entity;

public class GameEntity {

    private final int id;
    private final int trialCount;

    private GameEntity(final int id, final int trialCount) {
        this.id = id;
        this.trialCount = trialCount;
    }

    public static GameEntity of(final int id, final int trialCount) {
        return new GameEntity(id, trialCount);
    }

    public int getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
