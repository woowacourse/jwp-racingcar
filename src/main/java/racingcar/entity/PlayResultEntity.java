package racingcar.entity;

import java.sql.Timestamp;

public class PlayResultEntity {
    private final int id;
    private final String winners;
    private final int playCount;
    private final Timestamp createdAt;

    public PlayResultEntity(final int id, final String winners, final int playCount, final Timestamp createdAt) {
        this.id = id;
        this.winners = winners;
        this.playCount = playCount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public int getPlayCount() {
        return playCount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
