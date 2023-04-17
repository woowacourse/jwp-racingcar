package racingcar.repository.entity;

public class GameEntity {

    private final long id;
    private final int trialCount;
    private final String lastModifiedTime;

    public GameEntity(final long id, final int trialCount, final String lastModifiedTime) {
        this.id = id;
        this.trialCount = trialCount;
        this.lastModifiedTime = lastModifiedTime;
    }

    public GameEntity(final int trialCount) {
        this(0, trialCount, "");
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getLastModifiedTime() {
        return lastModifiedTime;
    }
}
