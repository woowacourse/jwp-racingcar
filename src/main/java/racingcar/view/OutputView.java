package racingcar.view;

import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.RacingGameResponse;

import java.util.List;

public class OutputView {
    public void printRacingResult(final RacingGameResponse response) {
        final List<CarDto> cars = response.getRacingCars();

        cars.forEach(car ->
                System.out.printf("%s : %s%n", car.getName(), "-" .repeat(car.getPosition())));
        System.out.printf("%n%n %s가 최종 우승했습니다.%n", response.getWinners());
    }
}
