package racing.ui.output;

import java.util.List;
import racing.domain.Car;
import racing.domain.Cars;

public class OutputView {

    private static final String EXECUTE_RESULT = "실행 결과";
    private static final String COLON = " : ";
    private static final String HYPHEN = "-";
    private static final String COMMA_DELIMITER = ", ";
    private static final String FINAL_WINNER_MESSAGE = "가 최종 우승했습니다.";

    private OutputView() {
    }

    public static void printResult(Cars cars) {
        System.out.println(EXECUTE_RESULT);
        for (Car car : cars.getCars()) {
            String printFormat = car.getName() + COLON + HYPHEN.repeat(car.getStep());
            System.out.println(printFormat);
        }

        printWinners(cars);
    }

    private static void printWinners(Cars cars) {
        List<String> winners = cars.getWinners();

        System.out.print(System.lineSeparator());
        System.out.println(String.join(COMMA_DELIMITER, winners) + FINAL_WINNER_MESSAGE);
    }
}
