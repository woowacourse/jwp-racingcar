package racingcar.controller.dto;

import racingcar.domain.Car;

import java.util.List;

public class PlaysResponseDto {

    private final String winners;
    private final List<Car> racingCars;

    public PlaysResponseDto(String winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }

    public static class Builder {

        private String winners;
        private List<Car> racingCars;

        public Builder winners(String winners) {
            this.winners = winners;
            return this;
        }

        public Builder racingCars(List<Car> racingCars) {
            this.racingCars = racingCars;
            return this;
        }

        public PlaysResponseDto build() {
            return new PlaysResponseDto(winners, racingCars);
        }

    }

}
