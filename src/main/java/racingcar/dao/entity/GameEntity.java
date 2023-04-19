package racingcar.dao.entity;

public class GameEntity {

    private final Long id;
    private final int trialCount;

    public GameEntity(final Long id, final int trialCount) {
        this.id = id;
        this.trialCount = trialCount;
    }

    public Long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
