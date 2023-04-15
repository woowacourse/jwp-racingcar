package racingcar.view;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

import racingcar.domain.Car;
import java.util.List;

public class OutputView {

    public void printResult(final List<Car> cars, final List<Car> winners) {
        System.out.println(lineSeparator() + "실행 결과");

        for (final Car car : cars) {
            System.out.println(car.getName() + " : " + car.getDistance());
        }

        String winnerNames = winners.stream()
                .map(Car::getName)
                .collect(joining(", "));
        System.out.println(winnerNames + "가 최종 우승했습니다.");
    }
}
