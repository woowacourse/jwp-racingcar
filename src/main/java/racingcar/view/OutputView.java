package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.Cars;

public class OutputView {
    private static final String WINNER_DELIMITER = ", ";
    private static final String PRINT_WINNER = "우승자:\n %s\n";
    public static final String PRINT_CAR_POSITION = "Name: %s, Position: %d\n";


    public static  void printResult(Cars cars) {
        System.out.printf(PRINT_WINNER, String.join(WINNER_DELIMITER, cars.getWinners()));

        System.out.println("결과: ");
        for (Car car : cars.getCars()) {
            System.out.printf(PRINT_CAR_POSITION, car.getName(), car.getLocation());
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
