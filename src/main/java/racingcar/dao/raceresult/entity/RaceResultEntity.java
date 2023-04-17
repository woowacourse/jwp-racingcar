package racingcar.dao.raceresult.entity;

import java.time.LocalDateTime;

public class RaceResultEntity {

    private final int id;
    private final int trialCount;
    private final String winners;
    private final LocalDateTime createdAt;

    public RaceResultEntity(final int id, final int trialCount,
                            final String winners, final LocalDateTime createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public static class Builder {

        private int id;
        private int trialCount;
        private String winners;
        private LocalDateTime createdAt;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder trialCount(int trialCount) {
            this.trialCount = trialCount;
            return this;
        }

        public Builder winners(String winners) {
            this.winners = winners;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RaceResultEntity build() {
            return new RaceResultEntity(id, trialCount, winners, createdAt);
        }
    }

    public int getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
