package racingcar.entity;

import java.sql.Timestamp;

public class GameEntity {
    private final Long id;
    private final Integer tryCount;
    private final Timestamp dateTime;

    public GameEntity(final Long id, final Integer tryCount, final Timestamp dateTime) {
        this.id = id;
        this.tryCount = tryCount;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }
}
