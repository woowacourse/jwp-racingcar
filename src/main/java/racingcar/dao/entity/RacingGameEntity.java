package racingcar.dao.entity;

import java.sql.Timestamp;

public class RacingGameEntity {

    private final Integer id;
    private final Integer count;
    private final Timestamp createdAt;

    public RacingGameEntity(Integer count) {
        this(null, count, null);
    }

    public RacingGameEntity(Integer id, Integer count, Timestamp createdAt) {
        this.id = id;
        this.count = count;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
