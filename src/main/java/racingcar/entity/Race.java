package racingcar.entity;

import racingcar.vo.Trial;

import java.sql.Timestamp;

public class Race {
    private final Integer id;
    private final int trialCount;
    private final Timestamp createdAt;

    public Race(int id, int trialCount, Timestamp createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public Race(Trial trial) {
        this.id = null;
        this.trialCount = trial.getValue();
        this.createdAt = null;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
