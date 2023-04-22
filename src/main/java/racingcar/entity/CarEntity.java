package racingcar.entity;

import java.time.LocalDateTime;

public class CarEntity {

    private final Long id;
    private final String name;
    private final int position;
    private final Long raceResultId;
    private final boolean winner;
    private final LocalDateTime createdAt;

    public CarEntity(final Long id, final String name,
                     final int position, final Long raceResultId,
                     final boolean winner, final LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.raceResultId = raceResultId;
        this.winner = winner;
        this.createdAt = createdAt;
    }

    public CarEntity(final String name, final int position,
                     final Long raceResultId, final boolean winner,
                     final LocalDateTime createdAt) {
        this(null, name, position, raceResultId, winner, createdAt);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Long getRaceResultId() {
        return raceResultId;
    }

    public boolean isWinner() {
        return winner;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
