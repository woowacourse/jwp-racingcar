package racingcar.dao;

import java.sql.Timestamp;

public class Racing {
    private final int id;
    private final int trialCount;
    private final Timestamp createdAt;

    public Racing(int id, int trialCount, Timestamp createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }
}
