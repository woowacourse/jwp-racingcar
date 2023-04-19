package racingcar.view;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String RESULT_MESSAGE = "\n실행 결과";
    private static final String WINNER_MESSAGE_FORMAT = "가 최종 우승했습니다.\n";
    public static final String DISTANCE_RESULT_FORMAT = "Name: %s, Position: %s\n";

    public void printResultMessage() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printFinalResult(List<Car> winners) {
        String winnerNames = winners.stream()
                .map(car -> car.getName())
                .collect(Collectors.joining(","));

        System.out.print(winnerNames + WINNER_MESSAGE_FORMAT);
    }

    public void printDistanceResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.printf(DISTANCE_RESULT_FORMAT, car.getName(), car.getPosition());
        }
    }
}
