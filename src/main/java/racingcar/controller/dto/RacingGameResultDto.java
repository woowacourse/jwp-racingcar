package racingcar.controller.dto;

import racingcar.domain.Cars;
import racingcar.domain.Winners;

public class RacingGameResultDto {

    private final int count;
    private final Cars cars;
    private final Winners winners;

    public RacingGameResultDto(int count, Cars cars, Winners winners) {
        this.count = count;
        this.cars = cars;
        this.winners = winners;
    }

    public int getCount() {
        return count;
    }

    public Cars getCars() {
        return cars;
    }

    public Winners getWinners() {
        return winners;
    }

    public Cars getRacingCars() {
        return cars;
    }

}
