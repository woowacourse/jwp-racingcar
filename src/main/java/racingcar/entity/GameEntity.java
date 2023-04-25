package racingcar.entity;

import java.time.LocalDateTime;

public class GameEntity {

    private final int id;
    private final int count;
    private final LocalDateTime createdAt;

    public GameEntity(int id, int count, LocalDateTime createdAt) {
        this.id = id;
        this.count = count;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "id=" + id +
                ", count=" + count +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder {

        private int id;
        private int count;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public GameEntity build() {
            return new GameEntity(id, count, LocalDateTime.now());
        }

    }

}
