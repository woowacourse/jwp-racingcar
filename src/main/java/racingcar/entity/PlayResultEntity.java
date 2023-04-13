package racingcar.entity;

import java.sql.Timestamp;

public class PlayResultEntity {

    private static final long ID_DUMMY_VALUE = 0;
    private final long id;
    private final int trialCount;
    private final String winners;
    private final Timestamp createdAt;

    private PlayResultEntity(long id, int trialCount, String winners, Timestamp createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public static PlayResultEntity of(int trialCount, String winners, Timestamp createdAt) {
        return new PlayResultEntity(ID_DUMMY_VALUE, trialCount, winners, createdAt);
    }

    public static PlayResultEntity of(long id, int trialCount, String winners, Timestamp createdAt) {
        return new PlayResultEntity(id, trialCount, winners, createdAt);
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
