package racingcar.entity;

import java.sql.Timestamp;

public class PlayResultEntity {

    private final long id;
    private final String winners;
    private final Timestamp createdAt;

    public PlayResultEntity(long id, String winners, Timestamp createdAt) {
        this.id = id;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
