package racingcar.entity;

import java.sql.Timestamp;

public class GameEntity {
    private final Long id;
    private final Integer tryCount;
    private final Timestamp createdAt;

    public GameEntity(final Long id, final Integer tryCount, final Timestamp createdAt) {
        this.id = id;
        this.tryCount = tryCount;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }
}
