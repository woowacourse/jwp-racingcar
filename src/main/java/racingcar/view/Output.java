package racingcar.view;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import racingcar.domain.Car;
import racingcar.domain.Winner;

public class Output {

    private static final String SEPERATOR = ", ";
    private static final String RESULT_FORMAT = "%s: %d%n";

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printCarsResult(List<Car> cars) {
        cars.forEach(Output::printCarResult);
    }

    private static void printCarResult(Car car) {
        System.out.printf(RESULT_FORMAT, car.getName(), car.getDistance());
    }

    public static void printWinner(Winner winner) {
        String winnerNames = winner.getWinnerNames().stream()
                .map(Objects::toString)
                .collect(Collectors.joining(SEPERATOR));
        System.out.println(winnerNames + "가 최종 우승했습니다.");
    }
}
