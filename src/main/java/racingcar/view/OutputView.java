package racingcar.view;

import racingcar.domain.Car;

import java.util.List;

public class OutputView {
    private static final String NAME_DELIMITER = ",";

    public static void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printWinners(final List<String> winnersNames) {
        System.out.println(String.format("%s가 최종 우승했습니다.", String.join(NAME_DELIMITER, winnersNames)));
    }

    public static void printCarResults(final List<Car> cars) {
        for (Car car : cars) {
            System.out.println(String.format("%s의 최종 포지션 : %d", car.getName(), car.getPosition()));
        }
    }
}
