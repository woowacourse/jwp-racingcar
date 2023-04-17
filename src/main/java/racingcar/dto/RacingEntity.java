package racingcar.dto;

import java.sql.Timestamp;

public class RacingEntity {

    private int id;
    private int totalCount;
    private Timestamp createdAt;

    public RacingEntity(int id, int totalCount, Timestamp createdAt) {
        this.id = id;
        this.totalCount = totalCount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
