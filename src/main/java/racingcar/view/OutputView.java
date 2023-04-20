package racingcar.view;

import racingcar.dto.CarDto;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = "-";
    private static final String WINNER_MSG = "%s(이)가 최종 우승했습니다.";
    private static final String FORMAT = "%s : %s";

    public static void printAllCars(List<CarDto> cars) {
        for (CarDto car : cars) {
            printCurrentState(car);
        }
        System.out.println();
    }

    private static void printCurrentState(CarDto car) {
        System.out.println(String.format(FORMAT, car.getName(), DELIMITER.repeat(car.getPosition())));
    }

    public static void printWinners(String winners) {
        System.out.println(String.format(WINNER_MSG, winners));
    }
}
