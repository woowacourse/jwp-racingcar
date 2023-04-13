package racingcar.controller.dto;

import racingcar.domain.Car;
import racingcar.entity.RacingGameEntity;

import java.util.List;

public class PlaysResponseDto {

    private final String winners;
    private final List<Car> racingCars;

    public PlaysResponseDto(String winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public PlaysResponseDto(RacingGameEntity racingGameEntity) {
        this(racingGameEntity.getWinners(), racingGameEntity.getRacingCars());
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }

}
