package racingcar.entity;

import racingcar.controller.dto.PlaysResponseDto;
import racingcar.domain.Car;

import java.time.LocalDateTime;
import java.util.List;

public class RacingGameEntity {

    private final int id;
    private final int count;
    private final String winners;
    private final LocalDateTime createdAt;
    private final List<Car> racingCars;

    public RacingGameEntity(int id, int count, String winners, LocalDateTime createdAt, List<Car> racingCars) {
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

    public List<Car> getRacingCars() {
        return racingCars;
    }

    public PlaysResponseDto toPlaysResponseDto() {
        return new PlaysResponseDto(this);
    }

    public static class Builder {

        private int id;
        private int count;
        private String winners;
        private List<Car> racingCars;

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

        public Builder racingCars(List<Car> racingCars) {
            this.racingCars = racingCars;
            return this;
        }

        public RacingGameEntity build() {
            return new RacingGameEntity(id, count, winners, LocalDateTime.now(), racingCars);
        }

    }

}
