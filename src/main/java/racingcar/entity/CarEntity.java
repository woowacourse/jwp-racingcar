package racingcar.entity;

import java.time.LocalDateTime;

public class CarEntity {

    private final Integer id;
    private final String name;
    private final int position;
    private final Integer raceResultId;
    private final LocalDateTime createdAt;

    private CarEntity(final Integer id, final String name,
                     final int position, final Integer raceResultId,
                     final LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.raceResultId = raceResultId;
        this.createdAt = createdAt;
    }

    public CarEntity(final String name, final int position,
                     final Integer raceResultId, final LocalDateTime createdAt) {
        this(null, name, position, raceResultId, createdAt);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Integer getRaceResultId() {
        return raceResultId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
