package racingcar.entity;

import java.time.LocalDateTime;
import java.util.List;

public class GameEntity {

    private int id;
    private final int count;
    private final String winners;
    private final LocalDateTime createdAt;

    public GameEntity(int id, int count, String winners, LocalDateTime createdAt) {
        this.id = id;
        this.count = count;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getWinners() {
        return winners;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Builder {

        private int id;
        private int count;
        private String winners;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder winners(String winners) {
            this.winners = winners;
            return this;
        }

        public GameEntity build() {
            return new GameEntity(id, count, winners, LocalDateTime.now());
        }

    }

}
