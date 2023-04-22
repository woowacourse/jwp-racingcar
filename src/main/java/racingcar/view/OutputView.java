package racingcar.view;

import racingcar.controller.response.CarResponse;
import racingcar.controller.response.RacingGameResponse;

import java.util.List;

public class OutputView {
    public void printRacingHistories(final List<RacingGameResponse> response) {
        response.forEach(this::printRacingResult);
    }

    public void printRacingResult(final RacingGameResponse response) {
        final List<CarResponse> cars = response.getRacingCars();

        cars.forEach(car ->
                System.out.printf("%s : %s%n", car.getName(), "-".repeat(car.getPosition())));
        System.out.printf("%n%n %s가 최종 우승했습니다.%n%n", response.getWinners());
    }
}
