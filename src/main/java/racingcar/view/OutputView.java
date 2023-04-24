package racingcar.view;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String WIN = "가 최종 우승했습니다.";

    public void printWinners(List<Car> winners) {
        final String DELIMITER = ", ";
        String message =
                winners.stream().map(Car::getName).collect(Collectors.joining(DELIMITER)) + WIN;
        System.out.println(message);
        System.out.println();
    }

    public void printParticipantsInfo(List<Car> participants) {
        for (Car car : participants) {
            System.out.println("name: " + car.getName());
            System.out.println("position: " + car.getDrivenDistance());
            System.out.println();
        }
    }

    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
