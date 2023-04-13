package racingcar.entity;

import java.time.LocalDateTime;
import java.util.List;

public class RacingGameEntity {

    private int id;
    private final int count;
    private final String winners;
    private final LocalDateTime createdAt;
    private final List<RacingCarEntity> racingCars;

    public RacingGameEntity(int id, int count, String winners, LocalDateTime createdAt, List<RacingCarEntity> racingCars) {
        this.id = id;
        this.count = count;
        this.winners = winners;
        this.createdAt = createdAt;
        this.racingCars = racingCars;
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

    public List<RacingCarEntity> getRacingCars() {
        return racingCars;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Builder {

        private int id;
        private int count;
        private String winners;
        private List<RacingCarEntity> racingCars;

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

        public Builder racingCars(List<RacingCarEntity> racingCars) {
            this.racingCars = racingCars;
            return this;
        }

        public RacingGameEntity build() {
            return new RacingGameEntity(id, count, winners, LocalDateTime.now(), racingCars);
        }

    }

}
