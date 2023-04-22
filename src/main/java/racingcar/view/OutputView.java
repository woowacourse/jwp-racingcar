package racingcar.view;

import racingcar.domain.Car;

import java.util.List;

public class OutputView {

    private static final String WINNERS_FORMAT = "%s가 최종 우승했습니다.%n";
    private static final String CAR_RESULT_FORMAT = "%s : %s\n";

    public void printResult(List<Car> cars) {
        cars.forEach(car -> System.out.print(printCarResult(car)));
        System.out.println();
    }

    private String printCarResult(Car car) {
        return String.format(
                CAR_RESULT_FORMAT,
                car.getName(),
                car.getPosition());
    }

    public void printWinners(String winners) {
        System.out.printf(WINNERS_FORMAT, winners);
    }

}
