package racingcar.repository.entity;

public class GameEntity {

    private final long id;
    private final int trialCount;
    private final String lastModifiedTime;

    public GameEntity(final int trialCount) {
        this.id = 0;
        this.trialCount = trialCount;
        this.lastModifiedTime = "";
    }

    public GameEntity(final long id, final int trialCount, final String lastModifiedTime) {
        this.id = id;
        this.trialCount = trialCount;
        this.lastModifiedTime = lastModifiedTime;
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
