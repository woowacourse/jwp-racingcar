package racingcar.view;

import racingcar.controller.dto.CarResponseDto;

import java.util.List;

public class OutputView {

    private static final String WINNERS_FORMAT = "%s가 최종 우승했습니다.%n";
    private static final String CAR_RESULT_FORMAT = "%s : %s\n";

    public void printResult(List<CarResponseDto> cars) {
        cars.forEach(car -> System.out.print(printCarResult(car)));
        System.out.println();
    }

    private String printCarResult(CarResponseDto car) {
        return String.format(
                CAR_RESULT_FORMAT,
                car.getName(),
                car.getPosition());
    }

    public void printWinners(String winners) {
        System.out.printf(WINNERS_FORMAT, winners);
    }

}