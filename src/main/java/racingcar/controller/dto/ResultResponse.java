package racingcar.controller.dto;

import java.util.List;

import racingcar.domain.Car;

public class ResultResponse {

    private final List<String> winners;
    private final List<Car> racingCars;

    public ResultResponse(List<String> winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    // getter가 직렬화에 쓰이는 것 같다.
    public List<String> getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
